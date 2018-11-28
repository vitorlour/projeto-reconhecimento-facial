/**
 * 
 */
package br.com.projeto.azure.service.impl;

import org.springframework.stereotype.Service;

import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.CreatePersonGroupsOptionalParameter;
import br.com.projeto.azure.conexao.ConexaoAzure;
import br.com.projeto.azure.service.PersonGroupService;

/**
 * @author vitor
 *
 */
@Service
public class PersonGroupServiceImpl implements PersonGroupService {
	
	private final static CreatePersonGroupsOptionalParameter groupParameter;
	
	static{
		groupParameter = new CreatePersonGroupsOptionalParameter();
	}
		
	public void criarGrupo(String personGroupId, String nome) {
		ConexaoAzure.ConectaAzureBrasil().personGroups().create(personGroupId, groupParameter.withName(nome));
	}
	
	public void trainGroup(String personGroupId) {
		ConexaoAzure.ConectaAzureBrasil().personGroups().train(personGroupId);
	}
	
}
