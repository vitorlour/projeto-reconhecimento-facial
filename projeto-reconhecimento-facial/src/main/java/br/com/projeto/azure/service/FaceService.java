package br.com.projeto.azure.service;

import com.azure.ai.vision.face.models.FaceDetectionResult;
import com.azure.ai.vision.face.models.FaceIdentifyCandidate;
import br.com.projeto.entity.RetornoIdentificarPessoa;

import java.util.List;
import java.util.UUID;

public interface FaceService {

    List<FaceDetectionResult> detectarRosto(byte[] imagem);

    List<UUID> getListaFaceIds(byte[] imagem);

    List<FaceIdentifyCandidate> identificarRosto(byte[] imagem);

    RetornoIdentificarPessoa identificarPessoas(byte[] imagem);
}
