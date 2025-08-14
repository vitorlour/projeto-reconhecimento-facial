package br.com.projeto.azure.service;

import com.azure.ai.vision.face.models.CreatePersonGroupPersonResult;
import com.azure.ai.vision.face.models.PersistedFace;

public interface PersonGroupPersonService {

    CreatePersonGroupPersonResult criarPessoaGrupo(String personGroupId, String nome);

    PersistedFace adicionarRostoPessoaGrupo(String personGroupId, String personId, byte[] imagem);

}
