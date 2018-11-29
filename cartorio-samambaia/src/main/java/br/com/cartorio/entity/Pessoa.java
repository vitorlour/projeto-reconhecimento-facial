/**
 * 
 */
package br.com.cartorio.entity;


import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Vitor Lourenço
 *
 */

@Getter
@Setter
public class Pessoa {

	private long id;

	@Expose
	@NotEmpty (message = "Preencha o nome")
	private String nome;

	@Expose
	@CPF(message="Cpf precisa ser válido")
	private String cpf;

	@Expose
	@NotEmpty (message = "Preencha o rg")
	private String rg;

	@Expose
	@NotEmpty (message = "Preencha o telefone")
	@Pattern(regexp = "[0-9]*", message = "Atenção, digite somente números")
	private String telefoneresidencial;

	@Expose
	@NotEmpty (message = "Preencha o e-mail")
	private String email;

	@Expose
	private String personId;

	@Expose
	private String personGroupId;

	@Expose
	private byte[] imagem;

	@Expose
	@Valid
	private Endereco endereco;
	
}
