/**
 * 
 */
package br.com.projeto.azure.service;

import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.APIErrorException;
import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.PersistedFace;
import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.Person;

/**
 * @author vitor
 *
 */
public interface PersonGroupPersonService {
	
	 Person criarPessoaGrupo(String personGroupId, String nome) throws APIErrorException;
	 
	 PersistedFace adicionarRostoPessoaGrupo(String personGroupId, String personId, byte[] imagem) throws APIErrorException;
	 
}
