/**
 * 
 */
package br.com.projeto.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.entity.Pessoa;
import br.com.projeto.repository.PessoaRepository;
import br.com.projeto.service.PessoaService;

/**
 * @author vitor
 *
 */
@Service
public class PessoaServiceImpl implements PessoaService {
	
	
	@Autowired
	private PessoaRepository repository;

	/* (non-Javadoc)
	 * @see br.com.projeto.service.PessoaService#save(br.com.projeto.entity.Pessoa)
	 */
	@Override
	public boolean save(Pessoa pessoa) {
		return repository.saveAndFlush(pessoa) != null;
	}

	/* (non-Javadoc)
	 * @see br.com.projeto.service.PessoaService#encontrarTodos()
	 */
	@Override
	public List<Pessoa> encontrarTodos() {
		return repository.findAll();
	}

	/* (non-Javadoc)
	 * @see br.com.projeto.service.PessoaService#encontrarPorId(java.lang.Long)
	 */
	@Override
	public Pessoa encontrarPorId(Long id) {
		
		return repository.findOne(id);
	}

	/* (non-Javadoc)
	 * @see br.com.projeto.service.PessoaService#excluir(java.lang.Long)
	 */
	@Override
	public void excluir(Pessoa pessoa) {
		repository.delete(pessoa);
	}

	@Override
	@Transactional
	public Pessoa encontrarPorPersonID(String personId) {
		return repository.findByPersonId(personId);
	}

}
