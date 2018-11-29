/**
 * 
 */
package br.com.cartorio.entity;

import javax.validation.constraints.NotEmpty;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Vitor Lourenço
 *
 */
@Getter @Setter
public class Endereco {
	
	private long id;
	
	@Expose
	@NotEmpty (message = "Preencha o endereço")
	private String endereco;
	
	@Expose
	@NotEmpty (message = "Preencha o número")
	private String numero;
	
	@Expose
	@NotEmpty (message = "Preencha o cep")
	private String cep;
	
	@Expose
	@NotEmpty(message = "Preencha o bairro")
	private String logradouro;
	
	@Expose
	@NotEmpty(message = "Preencha o bairro")
	private String bairro;
	
	@Expose
	@NotEmpty(message = "Preencha a cidade")
	private String cidade;
	
	@Expose
	@NotEmpty(message = "Preencha o estado")
	private String estado;
	
	@Expose
	@NotEmpty(message = "Preencha o pais")
	private String pais;
	
	@Expose
	private Pessoa pessoa;

	
}
