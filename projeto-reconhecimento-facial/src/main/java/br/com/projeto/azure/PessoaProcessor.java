/**
 * 
 */
package br.com.projeto.azure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.APIErrorException;
import br.com.projeto.azure.service.FaceService;
import br.com.projeto.azure.service.PersonGroupPersonService;
import br.com.projeto.azure.service.PersonGroupService;
import br.com.projeto.constants.Constantes;
import br.com.projeto.entity.Pessoa;
import br.com.projeto.entity.RetornoIdentificarPessoa;
import br.com.projeto.service.PessoaService;

/**
 * @author vitor
 *
 */
@Component
public class PessoaProcessor {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private FaceService faceService;

	@Autowired
	private PersonGroupPersonService personGroupPersonService;
	
	@Autowired
	private PersonGroupService personGroupService;

	public RetornoIdentificarPessoa salvarPessoaSemTrava(Pessoa pessoa) {
		
		RetornoIdentificarPessoa retornoIdentificarPessoa = new RetornoIdentificarPessoa();
		
		try {
			retornoIdentificarPessoa = faceService.identificarPessoas(pessoa.getImagem());
			
			if (retornoIdentificarPessoa != null) {
				pessoa.setPersonId(personGroupPersonService.criarPessoaGrupo(Constantes.GRUPO_ID, pessoa.getNome()).personId().toString());
				personGroupPersonService.adicionarRostoPessoaGrupo(Constantes.GRUPO_ID, pessoa.getPersonId(),pessoa.getImagem());
				pessoa.setPersonGroupId(Constantes.GRUPO_ID);
				if (pessoaService.save(pessoa)) {
					retornoIdentificarPessoa.setMensagem("Pessoa cadastrada com sucesso !");
					personGroupService.trainGroup(Constantes.GRUPO_ID);
				}
			}
		}catch (APIErrorException e) {
			retornoIdentificarPessoa.setMensagem(e.getMessage());
		}catch (Exception e) {
			retornoIdentificarPessoa.setMensagem(e.getMessage());
		}
		return retornoIdentificarPessoa;
	}
	
}
