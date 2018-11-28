/**
 * 
 */
package br.com.cartorio.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.cartorio.entity.Pessoa;
import br.com.cartorio.http.request.HttpRequestApi;

/**
 * @author vitor
 *
 */
@RestController
public class ConsumerAzureController {

	@Autowired
	private HttpRequestApi httpResquestApi;

	@GetMapping("/reconhecimento/")
	public ModelAndView add(MultipartFile fileUpload) {
		ModelAndView mv = new ModelAndView("/reconhecimentofacial");

		mv.addObject("fileUpload", fileUpload);

		return mv;
	}

	@PostMapping("/reconhecimento/")
	public ModelAndView reconhecimento(@RequestParam("fileUpload") MultipartFile fileUpload) throws IOException {
			
			ModelAndView mv = new ModelAndView("pessoasidentificadas");
			Pessoa pessoa = new Pessoa();
			pessoa.setImagem(fileUpload.getBytes());
			mv.addObject("pessoasIdentificadas",httpResquestApi.reconhecimentoFacial(pessoa));
		return mv;
	}
	
	
}
