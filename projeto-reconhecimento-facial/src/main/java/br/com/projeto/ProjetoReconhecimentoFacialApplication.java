package br.com.projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = { "br.com.projeto.entity" })
@EnableJpaRepositories(basePackages = { "br.com.projeto.repository" })
@ComponentScan(basePackages = {"br"})
public class ProjetoReconhecimentoFacialApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjetoReconhecimentoFacialApplication.class, args);
	}
}
