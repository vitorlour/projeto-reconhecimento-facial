/**
 * 
 */
package br.com.projeto.service;

import java.util.List;

import br.com.projeto.entity.Pessoa;

/**
 * @author vitor
 *
 */
public interface PessoaService {
	
	boolean save(Pessoa pessoa);
	
	List<Pessoa> encontrarTodos();
	
	Pessoa encontrarPorId(Long id);
	
	void excluir(Pessoa pessoa);
	
	Pessoa encontrarPorPersonID(String personId);

}
