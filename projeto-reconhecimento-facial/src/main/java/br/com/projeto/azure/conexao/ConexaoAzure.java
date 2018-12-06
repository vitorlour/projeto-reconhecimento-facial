/**
 * 
 */
package br.com.projeto.azure.conexao;

import br.com.microsoft.azure.cognitiveservices.vision.faceapi.FaceAPI;
import br.com.microsoft.azure.cognitiveservices.vision.faceapi.FaceAPIManager;
import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.APIErrorException;
import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.AzureRegions;
import br.com.projeto.constants.Constantes;

/**
 * @author vitor
 *
 */
public class ConexaoAzure {
	
	public static FaceAPI ConectaAzureBrasil() throws APIErrorException {
		return FaceAPIManager.authenticate(AzureRegions.BRAZILSOUTH, Constantes.subscriptionKey);
	}

}
