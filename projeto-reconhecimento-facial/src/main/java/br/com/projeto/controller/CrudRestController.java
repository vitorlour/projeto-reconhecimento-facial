/**
 * 
 */
package br.com.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.azure.PessoaProcessor;
import br.com.projeto.entity.Pessoa;
import br.com.projeto.entity.RetornoIdentificarPessoa;
import br.com.projeto.service.PessoaService;

/**
 * @author vitor
 *
 */
@RestController
@RequestMapping(value = "/pessoa/")
public class CrudRestController {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private PessoaProcessor pessoaProcessor;
	
	@RequestMapping(value = "encontrartodos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Pessoa> encontrarTodos() {
		return pessoaService.encontrarTodos();
	}
	
	@RequestMapping(value = "encontrarporid/{id}", method = RequestMethod.GET, produces = "application/json")
	public Pessoa encontrarPorId(@PathVariable("id") long id) {
		return pessoaService.encontrarPorId(id);
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Pessoa pessoa) {
		pessoaService.excluir(pessoa);
	}
	
	@RequestMapping(value = "salvar", method = RequestMethod.POST, consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public RetornoIdentificarPessoa save(@RequestBody Pessoa pessoa, BindingResult result)  {
			return pessoaProcessor.salvarPessoa(pessoa);
	}

}
