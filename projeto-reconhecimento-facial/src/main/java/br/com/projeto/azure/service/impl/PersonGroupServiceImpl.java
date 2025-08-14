/**
 *
 */
package br.com.projeto.azure.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.APIErrorException;
import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.CreatePersonGroupsOptionalParameter;
import br.com.projeto.azure.conexao.ConexaoAzure;
import br.com.projeto.azure.service.PersonGroupService;

/**
 * @author vitor
 *
 */
@Service
public class PersonGroupServiceImpl implements PersonGroupService {

	@Autowired
	private ConexaoAzure conexaoAzure;

	private final static CreatePersonGroupsOptionalParameter groupParameter;

	static {
		groupParameter = new CreatePersonGroupsOptionalParameter();
	}

	public void criarGrupo(String personGroupId, String nome) throws APIErrorException {
		conexaoAzure.conectaAzureBrasil().personGroups().create(personGroupId, groupParameter.withName(nome));
	}

	public void trainGroup(String personGroupId) throws APIErrorException {
		conexaoAzure.conectaAzureBrasil().personGroups().train(personGroupId);
	}

}
