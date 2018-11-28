/**
 * 
 */
package br.com.projeto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Vitor Lourenço
 *
 */
@Entity
@Table(name = "ENDERECO")
@Getter @Setter
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="endereco", nullable=false)
	@NotBlank(message = "Preencha o endereço")
	@NotNull (message = "Preencha o endereço")
	private String endereco;
	
	@Column(name="numero", nullable=false)
	@NotBlank(message = "Preencha o número")
	@NotNull (message = "Preencha o número")
	private String numero;
	
	@Column(name="cep", nullable=false)
	@NotBlank(message = "Preencha o cep")
	@NotNull (message = "Preencha o cep")
	private String cep;
	
	@Column(name="logradouro", nullable=true)
	@NotNull (message = "Preencha o logradouro")
	@NotBlank(message = "Preencha o logradouro")
	private String logradouro;
	
	@Column(name="bairro", nullable=false)
	@NotBlank(message = "Preencha o bairro")
	@NotNull (message = "Preencha o bairro")
	private String bairro;
	
	@Column(name="cidade", nullable=false)
    @NotBlank(message = "Preencha a cidade")
	@NotNull (message = "Preencha a cidade")
	private String cidade;
	
	@Column(name="estado", nullable=false)
	@NotBlank(message = "Preencha o estado")
	@NotNull (message = "Preencha o estado")
	private String estado;
	
	@Column(name="pais", nullable=false)
	@NotBlank(message = "Preencha o pais")
	@NotNull (message = "Preencha o pais")
	private String pais;
	
	@JsonBackReference
	@OneToOne (mappedBy="endereco")
	private Pessoa pessoa;

	
}
