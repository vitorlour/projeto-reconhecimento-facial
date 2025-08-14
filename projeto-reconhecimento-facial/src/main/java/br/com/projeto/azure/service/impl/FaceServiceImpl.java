package br.com.projeto.azure.service.impl;

import com.azure.ai.vision.face.FaceClient;
import com.azure.ai.vision.face.models.*;
import com.azure.core.util.BinaryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.projeto.azure.conexao.ConexaoAzure;
import br.com.projeto.azure.service.FaceService;
import br.com.projeto.constants.Constantes;
import br.com.projeto.entity.PessoasIdentificadas;
import br.com.projeto.entity.RetornoIdentificarPessoa;
import br.com.projeto.service.PessoaService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FaceServiceImpl implements FaceService {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ConexaoAzure conexaoAzure;

    public List<FaceDetectionResult> detectarRosto(byte[] imagem) {
        FaceClient faceClient = conexaoAzure.conectaAzure();
        BinaryData binaryData = BinaryData.fromBytes(imagem);
        return faceClient.detect(binaryData, FaceDetectionModel.DETECTION_03, FaceRecognitionModel.RECOGNITION_04, true, null, true, true, 120);
    }

    public List<UUID> getListaFaceIds(byte[] imagem) {
        List<FaceDetectionResult> listaRostosDetectados = detectarRosto(imagem);
        return listaRostosDetectados.stream().map(FaceDetectionResult::getFaceId).collect(Collectors.toList());
    }

    public List<FaceIdentifyCandidate> identificarRosto(byte[] imagem) {
        FaceClient faceClient = conexaoAzure.conectaAzure();
        List<UUID> listaFaceIds = getListaFaceIds(imagem);
        if (listaFaceIds == null || listaFaceIds.isEmpty()) {
            return Collections.emptyList();
        }
        List<FaceIdentifyResult> result = faceClient.identify(Constantes.GRUPO_ID, listaFaceIds, 50, 0.5);
        return result.stream()
                .flatMap(identifyResult -> identifyResult.getCandidates().stream())
                .collect(Collectors.toList());
    }

    public RetornoIdentificarPessoa identificarPessoas(byte[] imagem) {
        List<FaceIdentifyCandidate> listaRostosIdentificados = identificarRosto(imagem);
        List<PessoasIdentificadas> listaPessoasIdentificadas = new ArrayList<>();
        RetornoIdentificarPessoa retornoPessoa = new RetornoIdentificarPessoa();

        if (listaRostosIdentificados != null) {
            for (FaceIdentifyCandidate candidato : listaRostosIdentificados) {
                if (pessoaService.existePersonId(candidato.getPersonId().toString())) {
                    PessoasIdentificadas pessoaIdentificada = pessoaService
                            .encontrarPessoasIdentificadasPorPersonID(candidato.getPersonId().toString());
                    listaPessoasIdentificadas.add(new PessoasIdentificadas(pessoaIdentificada.getId(),
                            pessoaIdentificada.getNome(), pessoaIdentificada.getCpf(),
                            pessoaIdentificada.getImagem(), candidato.getConfidence() * 100));
                }
            }
            if (!listaPessoasIdentificadas.isEmpty()) {
                retornoPessoa.setIdentificarPessoa(listaPessoasIdentificadas);
            }
        }
        return retornoPessoa;
    }
}
