/**
 * 
 */
package br.com.projeto.azure.service.impl;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.AddPersonFaceFromStreamOptionalParameter;
import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.CreatePersonGroupPersonsOptionalParameter;
import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.PersistedFace;
import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.Person;
import br.com.projeto.azure.conexao.ConexaoAzure;
import br.com.projeto.azure.service.PersonGroupPersonService;

/**
 * @author vitor
 *
 */
@Service
public class PersonGroupPersonServiceImpl implements PersonGroupPersonService {

	private final static CreatePersonGroupPersonsOptionalParameter groupPerson;

	private final static AddPersonFaceFromStreamOptionalParameter faceStreamParameter;

	static {
		groupPerson = new CreatePersonGroupPersonsOptionalParameter();
		faceStreamParameter = new AddPersonFaceFromStreamOptionalParameter();
	}

	public Person criarPessoaGrupo(String personGroupId, String nome) {
		return ConexaoAzure.ConectaAzureBrasil().personGroupPersons().create(personGroupId, groupPerson.withName(nome));
	}

	public PersistedFace adicionarRostoPessoaGrupo(String personGroupId, String personId, byte[] imagem) {
		ZonedDateTime now = ZonedDateTime.now();
		UUID personIdUUID = UUID.fromString(personId);
		return ConexaoAzure.ConectaAzureBrasil().personGroupPersons().addPersonFaceFromStream(personGroupId, personIdUUID,
				imagem, faceStreamParameter.withUserData(now.toString()));
	}

}
