/**
 * 
 */
package br.com.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.entity.Pessoa;

/**
 * @author vitor
 *
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	@Query
	Pessoa findByPersonId(@Param("personid") String personid);
	
}
