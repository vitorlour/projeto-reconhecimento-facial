package br.com.cartorio.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.cartorio.entity.Pessoa;
import br.com.cartorio.entity.RetornoIdentificarPessoa;
import br.com.cartorio.http.request.HttpRequestApi;
import br.com.cartorio.processor.PessoaProcessor;

@Controller
public class ConsumerCrudController {

	@Autowired
	private HttpRequestApi httpResquestApi;

	@Autowired
	private PessoaProcessor pessoaProcessor;

	@GetMapping("/")
	public ModelAndView findAll() {

		ModelAndView mv = new ModelAndView("/inicio");

		mv.addObject("pessoa", httpResquestApi.encontratodosRequest());

		return mv;

	}

	@GetMapping("/save")
	public ModelAndView add(Pessoa pessoa) {
		ModelAndView mv;
		
		if (pessoa.getId() > 0) {
			mv = new ModelAndView("/editar");
		} else {
			mv = new ModelAndView("/salvar");
		}
		mv.addObject("pessoa", pessoa);
		return mv;
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		return add(httpResquestApi.encontraPorIdRequest(id));
	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		httpResquestApi.deleteRequest(id);
		
		return findAll();
	}
	
	@RequestMapping(value = "/image/{image_id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("image_id") Long id) throws IOException {
    	
    	Pessoa pessoa = httpResquestApi.encontraPorIdRequest(id);
    	
        byte[] imageContent = pessoa.getImagem() ;
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
        
    }
	

	@PostMapping("/save")
	public ModelAndView save(@Valid Pessoa pessoa, BindingResult result,
			@RequestParam("fileUpload") MultipartFile fileUpload) throws IOException {

		if (result.hasErrors()) {
			return add(pessoa);
		}
		try {
			RetornoIdentificarPessoa retorno = pessoaProcessor.retornoSalvarPessoa(pessoa, fileUpload);

			System.out.println(retorno.getMensagem());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return findAll();
	}

}
