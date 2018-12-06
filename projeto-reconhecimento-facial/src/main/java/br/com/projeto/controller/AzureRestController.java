package br.com.projeto.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.DetectedFace;
import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.IdentifyCandidate;
import br.com.projeto.azure.service.FaceService;
import br.com.projeto.azure.service.PersonGroupService;
import br.com.projeto.entity.Imagem;
import br.com.projeto.entity.RetornoIdentificarPessoa;

@RestController
@RequestMapping(value = "/azure/")
public class AzureRestController {

	@Autowired
	private FaceService faceService;

	@Autowired
	private PersonGroupService personGroupService;

	@RequestMapping(value = "detectarrosto/", method = RequestMethod.POST)
	public List<DetectedFace> detectarRosto(@RequestBody Imagem imagem) throws IOException {
		return faceService.detectarRosto(imagem.getImagem());
	}

	@RequestMapping(value = "listafaceids/", method = RequestMethod.POST)
	public List<UUID> getListaFaceIds(@RequestBody Imagem imagem) throws IOException {
		return faceService.getListaFaceIds(imagem.getImagem());
	}
	
	@RequestMapping(value = "listarostosidentificados/", method = RequestMethod.POST)
	public List<IdentifyCandidate> identificarRosto(@RequestBody Imagem imagem) throws IOException {
		return faceService.identificarRosto(imagem.getImagem());
	}
	
	@RequestMapping(value = "listapessoasidentificadas/", method = RequestMethod.POST,  produces = "application/json")
	public RetornoIdentificarPessoa identificarPessoas(@RequestBody Imagem imagem) throws IOException {
		return faceService.identificarPessoas(imagem.getImagem());
	}
	
	@RequestMapping(value = "criargrupo/", method = RequestMethod.POST)
	public void criarGrupoAzure(@RequestBody String personGroupId,
			@RequestParam(value = "nome") String nome) {
		personGroupService.criarGrupo(personGroupId, nome);
	}
}
