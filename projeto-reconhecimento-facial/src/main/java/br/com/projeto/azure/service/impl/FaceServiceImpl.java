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
import br.com.projeto.entity.Pessoa;
import br.com.projeto.entity.RetornoIdentificarPessoa;
import br.com.projeto.service.PessoaService;

@Service
public class FaceServiceImpl implements FaceService {

	@Autowired
	PessoaService pessoaService;

	private final static DetectWithStreamOptionalParameter streamParameter;

	private final static IdentifyOptionalParameter identifyParameter;

	static {
		streamParameter = new DetectWithStreamOptionalParameter();
		identifyParameter = new IdentifyOptionalParameter();
	}

	public List<DetectedFace> detectarRosto(byte[] imagem) {
		
		List<DetectedFace> listaRostosDetectados = ConexaoAzure.ConectaAzureBrasil().faces().detectWithStream(imagem,
				streamParameter.withReturnFaceId(true));

		return listaRostosDetectados;
	}

	public List<UUID> getListaFaceIds(byte[] imagem) {
		List<DetectedFace> listaRostosDetectados = detectarRosto(imagem);

		List<UUID> listaFaceIds = new ArrayList<UUID>();

		for (DetectedFace listaRostos : listaRostosDetectados) {
			listaFaceIds.add(listaRostos.faceId());
		}

		return listaFaceIds;
	}

	public List<IdentifyCandidate> identificarRosto(byte[] imagem) {

		List<UUID> listaFaceIds = getListaFaceIds(imagem);

		List<IdentifyCandidate> retornoListaRostosIdentificados = new ArrayList<IdentifyCandidate>();
		try {
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
			e.getMessage();
		}
		return retornoListaRostosIdentificados;
	}

	public RetornoIdentificarPessoa identificarPessoas(byte[] imagem) {

		List<IdentifyCandidate> listaRostosIdentificados = identificarRosto(imagem);

		List<Pessoa> listaPessoasIdentificadas = new ArrayList<Pessoa>();

		RetornoIdentificarPessoa retornoPessoa = new RetornoIdentificarPessoa();

		List<PessoasIdentificadas> listaIdentificarPessoa = new ArrayList<PessoasIdentificadas>();
		try {
			if (listaRostosIdentificados != null) {

				for (IdentifyCandidate listaRostos : listaRostosIdentificados) {
					listaPessoasIdentificadas
							.add(pessoaService.encontrarPorPersonID(listaRostos.personId().toString()));
				}
				if (listaPessoasIdentificadas != null) {
					for (Pessoa pessoa : listaPessoasIdentificadas) {
						if (pessoa != null) {
							listaIdentificarPessoa
									.add((new PessoasIdentificadas(pessoa.getId(), pessoa.getNome(), pessoa.getCpf())));
						}
						if (listaIdentificarPessoa != null) {
							retornoPessoa.setIdentificarPessoa(listaIdentificarPessoa);
						}
					}
				}
			}
		} catch (APIErrorException e) {
			e.getCause();
		}
		return retornoPessoa;
	}
}
