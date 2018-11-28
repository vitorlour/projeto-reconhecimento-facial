/**
 * 
 */
package br.com.projeto.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.DetectedFace;
import br.com.microsoft.azure.cognitiveservices.vision.faceapi.models.IdentifyCandidate;
import br.com.projeto.azure.PessoaProcessor;
import br.com.projeto.azure.service.impl.FaceServiceImpl;
import br.com.projeto.entity.Endereco;
import br.com.projeto.entity.PessoasIdentificadas;
import br.com.projeto.entity.Pessoa;
import br.com.projeto.entity.RetornoIdentificarPessoa;
import br.com.projeto.service.PessoaService;
import br.com.projeto.util.imagemUtil;

/**
 * @author vitor
 *
 */

@SpringBootApplication
@EntityScan(basePackages = { "br.com.projeto.entity" })
@EnableJpaRepositories(basePackages = { "br.com.projeto.repository" })
@ComponentScan(basePackages = { "br" })
public class testAzureCrud implements CommandLineRunner {

	
	@Autowired
	private PessoaProcessor pessoaProcessador;
	
	@Autowired 
	PessoaService pessoaService;
	
	@Autowired
	FaceServiceImpl faceServiceImpl;
	
	static Map<Integer, String> imagemPath;

	static {
		imagemPath = new HashMap<Integer, String>();

		imagemPath.put(1, "C:\\images");
		imagemPath.put(2, "C:\\images");

	}

	public static void main(String[] args) {
		SpringApplication.run(testCrud.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		try {
			testAzureApi();
			// dadosPessoa();
			//salvarPessoa();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	private Pessoa dadosPessoa() {

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

		return vitor;

	}
	
	public void testAzureApi() throws IOException{/*
		
		
	//	Pessoa pessoa = pessoaService.encontrarPorId(2L);
		
		List<DetectedFace> listafaces = faceServiceImpl.detectarRosto(imagemUtil.extractBytes("C:\\482444_307494502711242_1732259166_n.jpg"));
		
		for (DetectedFace detectedFace : listafaces) {
			System.out.println(detectedFace.faceId());
		}
		
		try {
			List<IdentifyCandidate> retornoListaRostosIdentificados = faceServiceImpl.identificarRosto(imagemUtil.extractBytes("C:\\482444_307494502711242_1732259166_n.jpg"));
			
			for (IdentifyCandidate identifyCandidate : retornoListaRostosIdentificados) {
				System.out.println("Pèrson ID: " + identifyCandidate.personId() + " Confiança " + identifyCandidate.confidence());
			}
			
			RetornoIdentificarPessoa retornoListaPessoas = faceServiceImpl.identificarPessoas(imagemUtil.extractBytes("C:\\482444_307494502711242_1732259166_n.jpg"));
			for (PessoasIdentificadas pessoa2 : retornoListaPessoas.getIdentificarPessoa()) {
				if(pessoa2 != null) {
				System.out.println(pessoa2.toString());
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	*/}

	/*public void salvarPessoa() {

		pessoaProcessador.salvarPessoa(imagemPath.get(1), dadosPessoa());

	}*/

}
