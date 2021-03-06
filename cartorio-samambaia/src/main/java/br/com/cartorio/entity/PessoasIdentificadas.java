/**
 * 
 */
package br.com.cartorio.entity;

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
public class PessoasIdentificadas {

	private Long id;
	private String nome;
	private String cpf;
	private double confianca;
	private byte[] imagem;

	public PessoasIdentificadas(Long id, String nome, String cpf, byte[] imagem, double confianca) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.imagem = imagem;
		this.confianca = confianca;
	}

}
