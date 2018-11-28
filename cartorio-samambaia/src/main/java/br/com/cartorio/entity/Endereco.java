/**
 * 
 */
package br.com.cartorio.entity;

import javax.validation.constraints.NotNull;

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
	@NotNull (message = "Preencha o endereço")
	private String endereco;
	
	@Expose
	@NotNull (message = "Preencha o número")
	private String numero;
	
	@Expose
	@NotNull (message = "Preencha o cep")
	private String cep;
	
	@Expose
	@NotNull(message = "Preencha o bairro")
	private String logradouro;
	
	@Expose
	@NotNull (message = "Preencha o bairro")
	private String bairro;
	
	@Expose
	@NotNull (message = "Preencha a cidade")
	private String cidade;
	
	@Expose
	@NotNull (message = "Preencha o estado")
	private String estado;
	
	@Expose
	@NotNull (message = "Preencha o pais")
	private String pais;
	
	@Expose
	private Pessoa pessoa;

	
}
