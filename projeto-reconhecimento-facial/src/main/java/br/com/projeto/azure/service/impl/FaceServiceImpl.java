package br.com.projeto.azure.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.APIErrorException;
import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.DetectWithStreamOptionalParameter;
import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.DetectedFace;
import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.IdentifyCandidate;
import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.IdentifyOptionalParameter;
import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.IdentifyResult;
import br.com.projeto.azure.conexao.ConexaoAzure;
import br.com.projeto.azure.service.FaceService;
import br.com.projeto.constants.Constantes;
import br.com.projeto.entity.PessoasIdentificadas;
import br.com.projeto.entity.RetornoIdentificarPessoa;
import br.com.projeto.service.PessoaService;

@Service
public class FaceServiceImpl implements FaceService {

	@Autowired
	private PessoaService pessoaService;

	private final static DetectWithStreamOptionalParameter streamParameter;

	private final static IdentifyOptionalParameter identifyParameter;

	static {
		streamParameter = new DetectWithStreamOptionalParameter();
		identifyParameter = new IdentifyOptionalParameter();
	}

	public List<DetectedFace> detectarRosto(byte[] imagem) throws APIErrorException {
		
		List<DetectedFace> listaRostosDetectados = ConexaoAzure.ConectaAzureBrasil().faces().detectWithStream(imagem,
				streamParameter.withReturnFaceId(true));

		return listaRostosDetectados;
	}

	public List<UUID> getListaFaceIds(byte[] imagem) throws APIErrorException {
		List<DetectedFace> listaRostosDetectados = detectarRosto(imagem);

		List<UUID> listaFaceIds = new ArrayList<UUID>();

		for (DetectedFace listaRostos : listaRostosDetectados) {
			listaFaceIds.add(listaRostos.faceId());
		}

		return listaFaceIds;
	}

	public List<IdentifyCandidate> identificarRosto(byte[] imagem) {
		
		List<IdentifyCandidate> retornoListaRostosIdentificados = new ArrayList<IdentifyCandidate>();
		
		try {
			
			List<UUID> listaFaceIds = getListaFaceIds(imagem);
			
			if (listaFaceIds != null) {

				List<IdentifyResult> listaRostosIdentificados = ConexaoAzure.ConectaAzureBrasil().faces().identify(
						Constantes.GRUPO_ID, listaFaceIds, identifyParameter.withMaxNumOfCandidatesReturned(50));

				if (listaRostosIdentificados != null) {
					for (IdentifyResult listaRostos : listaRostosIdentificados) {
						retornoListaRostosIdentificados.addAll(listaRostos.candidates());
					}
				}
			}
		} catch (APIErrorException e) {
		}
		return retornoListaRostosIdentificados;
	}

	public RetornoIdentificarPessoa identificarPessoas(byte[] imagem) {

		List<IdentifyCandidate> listaRostosIdentificados = identificarRosto(imagem);

		List<PessoasIdentificadas> listaPessoasIdentificadas = new ArrayList<PessoasIdentificadas>();

		RetornoIdentificarPessoa retornoPessoa = new RetornoIdentificarPessoa();

		try {
			if (listaRostosIdentificados != null) {

				for (IdentifyCandidate listaRostos : listaRostosIdentificados) {
					if (pessoaService.existePersonId(listaRostos.personId().toString())) {
						PessoasIdentificadas pessoaIdentificada = pessoaService
								.encontrarPessoasIdentificadasPorPersonID(listaRostos.personId().toString());
						listaPessoasIdentificadas.add(new PessoasIdentificadas(pessoaIdentificada.getId(),
								pessoaIdentificada.getNome(), pessoaIdentificada.getCpf(), 
								pessoaIdentificada.getImagem(), listaRostos.confidence()*100));
					}
				}
				if (listaPessoasIdentificadas != null) {
					retornoPessoa.setIdentificarPessoa(listaPessoasIdentificadas);
				}
			}
		} catch (APIErrorException e) {
		}
		return retornoPessoa;
	}
}
