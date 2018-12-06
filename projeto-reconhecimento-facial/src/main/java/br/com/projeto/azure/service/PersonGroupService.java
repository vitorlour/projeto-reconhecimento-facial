/**
 * 
 */
package br.com.projeto.azure.service;

import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.APIErrorException;

/**
 * @author vitor
 *
 */
public interface PersonGroupService {
	
	void criarGrupo(String personGroupId, String nome) throws APIErrorException;
		
	void trainGroup(String personGroupId) throws APIErrorException;

}
