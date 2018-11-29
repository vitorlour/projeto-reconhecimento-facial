/**
 * 
 */
package br.com.projeto.azure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.APIErrorException;
import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.DetectedFace;
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

	public RetornoIdentificarPessoa salvarPessoa(Pessoa pessoa) {

		RetornoIdentificarPessoa retornoIdentificarPessoa = new RetornoIdentificarPessoa();
		List<DetectedFace> listaDeRosto = null;

		try {
			listaDeRosto = faceService.detectarRosto(pessoa.getImagem());
			if (listaDeRosto.size() == 1) {
				retornoIdentificarPessoa = faceService.identificarPessoas(pessoa.getImagem());

				if (retornoIdentificarPessoa != null) {
					pessoa.setPersonId(personGroupPersonService.criarPessoaGrupo(Constantes.GRUPO_ID, pessoa.getNome())
							.personId().toString());
					personGroupPersonService.adicionarRostoPessoaGrupo(Constantes.GRUPO_ID, pessoa.getPersonId(),
							pessoa.getImagem());
					pessoa.setPersonGroupId(Constantes.GRUPO_ID);
					if (pessoaService.save(pessoa)) {
						retornoIdentificarPessoa.setMensagem(Constantes.MENSAGEM_CADASTRO_COM_SUCESSO);
						personGroupService.trainGroup(Constantes.GRUPO_ID);
					}
				}
			} else {
				retornoIdentificarPessoa.setMensagem(Constantes.MENSAGEM_MAIS_DE_UM_ROSTO_NA_IMAGEM);
			}
		}catch (DataIntegrityViolationException c){
			retornoIdentificarPessoa.setMensagem(Constantes.MENSAGEM_CPF_DUPLICADO);
		} catch (APIErrorException e) {
			retornoIdentificarPessoa.setMensagem(e.getMessage());
		} catch (Exception e) {
			retornoIdentificarPessoa.setMensagem(e.getMessage());
		}
		return retornoIdentificarPessoa;
	}

}
