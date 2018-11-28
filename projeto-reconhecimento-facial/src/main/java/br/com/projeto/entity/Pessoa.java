/**
 * 
 */
package br.com.projeto.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Vitor Lourenço
 *
 */

@Entity
@Table(name = "PESSOA")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="nome", nullable=false)
	@NotBlank(message = "Preencha o nome")
	@NotNull (message = "Preencha o nome")
	private String nome;
	
	@CPF(message="Cpf precisa ser válido")
	@Column(name="cpf", unique = true, nullable=false)
	private String cpf;
	
	@Column(name="rg", nullable=false)
	@NotBlank(message = "Preencha o rg")
	@NotNull (message = "Preencha o rg")
	private String rg;
	
	@Column(name="telefoneresidencial", nullable=false)
	@NotBlank(message = "Preencha o telefone")
	@NotNull (message = "Preencha o telefone")
	@Pattern(regexp = "[0-9]*", message = "Atenção, digite somente números")
	private String telefoneresidencial;
	
	@Email(message="E-mail precisa ser válido")
	@Column(name="email", nullable=false)
	@NotBlank(message = "Preencha o e-mail")
	@NotNull (message = "Preencha o e-mail")
	private String email;
	
	@Column(name="personid", nullable=true)
	private String personId;
	
	@Column(name="persongroupid", nullable=true)
	private String personGroupId;
	
	@Column(name="imagempath", nullable=true)
	private String imagemPath;
	
	@Lob
	@Column(name="imagem", nullable=false, columnDefinition="mediumblob")
	private byte[] imagem;
	
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="endereco_id")
	@Valid
	private Endereco endereco;

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", telefoneresidencial="
				+ telefoneresidencial + ", email=" + email + ", personId=" + personId + ", personGroupId="
				+ personGroupId + "endereco=" + endereco + "]";
	}
	
	
}
