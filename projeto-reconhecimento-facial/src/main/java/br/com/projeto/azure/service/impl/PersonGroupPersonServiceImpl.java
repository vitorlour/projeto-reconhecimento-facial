package br.com.projeto.azure.service.impl;

import com.azure.ai.vision.face.FaceAdministrationClient;
import com.azure.ai.vision.face.models.CreatePersonGroupPersonResult;
import com.azure.ai.vision.face.models.PersonGroupPerson;
import com.azure.core.util.BinaryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.projeto.azure.conexao.ConexaoAzure;
import br.com.projeto.azure.service.PersonGroupPersonService;

import java.util.UUID;

@Service
public class PersonGroupPersonServiceImpl implements PersonGroupPersonService {

    @Autowired
    private ConexaoAzure conexaoAzure;

    @Override
    public CreatePersonGroupPersonResult criarPessoaGrupo(String personGroupId, String nome) {
        FaceAdministrationClient adminClient = conexaoAzure.conectaAzureAdmin();
        return adminClient.createPersonGroupPerson(personGroupId, new PersonGroupPerson().setName(nome));
    }

    @Override
    public com.azure.ai.vision.face.models.PersistedFace adicionarRostoPessoaGrupo(String personGroupId, String personId, byte[] imagem) {
        FaceAdministrationClient adminClient = conexaoAzure.conectaAzureAdmin();
        BinaryData binaryData = BinaryData.fromBytes(imagem);
        return adminClient.addPersonGroupPersonFace(personGroupId, UUID.fromString(personId), binaryData, null, null);
    }
}
