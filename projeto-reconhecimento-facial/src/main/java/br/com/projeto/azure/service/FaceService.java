/**
 * 
 */
package br.com.projeto.azure.service;

import java.util.List;
import java.util.UUID;

import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.APIErrorException;
import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.DetectedFace;
import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.IdentifyCandidate;
import br.com.projeto.entity.RetornoIdentificarPessoa;

/**
 * @author vitor
 *
 */
public interface FaceService {
	
	List<DetectedFace> detectarRosto(byte[] imagem) throws APIErrorException;
	
	List<UUID> getListaFaceIds(byte[] imagem) throws APIErrorException;
	
	List<IdentifyCandidate> identificarRosto(byte[] imagem);
	
	RetornoIdentificarPessoa identificarPessoas(byte[] imagem);
}
