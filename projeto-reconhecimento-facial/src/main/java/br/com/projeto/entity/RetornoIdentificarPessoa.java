/**
 * 
 */
package br.com.projeto.entity;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author vitor
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RetornoIdentificarPessoa {

	private String mensagem;
	private List<PessoasIdentificadas> identificarPessoa;
	
}
