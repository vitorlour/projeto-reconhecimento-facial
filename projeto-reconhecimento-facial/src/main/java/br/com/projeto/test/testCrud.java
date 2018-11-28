/**
 * 
 */
package br.com.projeto.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author vitor
 *
 */
@SpringBootApplication
@EntityScan(basePackages = { "br.com.projeto.entity" })
@EnableJpaRepositories(basePackages = { "br.com.projeto.repository" })
@ComponentScan(basePackages = {"br"})
public class testCrud implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}/*

	@Autowired
	private PessoaService pessoaService;

	public static void main(String[] args) {
		SpringApplication.run(testCrud.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		try {
			
		//	saveData();
			showData();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Transactional
	private void saveData() {
		
		Pessoa vitor = new Pessoa();
		vitor.setNome("Vitor Lourenco");
		vitor.setCpf("09055661813");
		vitor.setRg("438709895");
		vitor.setEmail("vitorlour@hotmail.com");
		
		
		Endereco enderecoVitor = new Endereco();
		enderecoVitor.setEndereco("Rua Escocia");
		enderecoVitor.setNumero("382");
		enderecoVitor.setCep("06033050");
		enderecoVitor.setLogradouro("casa3");
		enderecoVitor.setBairro("Jd Dabril");
		enderecoVitor.setCidade("Osasco");
		enderecoVitor.setEstado("São Paulo");
		enderecoVitor.setPais("brasil");
		
		vitor.setEndereco(enderecoVitor);
		
		pessoaService.save(vitor);

		Pessoa paulo = new Pessoa("paulo sabao","06558215478","438709895","11359181111","paulo@outlook.com", new Endereco("Av vital brasil","382","06000000",
				"Usjt","Butanta","SP","São paulo","Brasil"));
		
	//	pessoaService.save(paulo);
		
		
	//	pessoaService.save(Arrays.asList(vitor, paulo));
	}

	@Transactional
	private void showData() {
		// get All data
		try{
		//	pessoaService.excluir(2L);	
		List<Pessoa> pessoa = pessoaService.encontrarTodos();
		
		
		//List<Endereco> endereco = enderecoService.encontrarTodos();
		
		System.out.println("===================nome:==================");
		pessoa.forEach(System.out::println);

	//	System.out.println("===================endereco:==================");
	//	endereco.forEach(System.out::println);
		
		}catch (Exception e) {
			e.printStackTrace();
		}

	
	}

*/}
