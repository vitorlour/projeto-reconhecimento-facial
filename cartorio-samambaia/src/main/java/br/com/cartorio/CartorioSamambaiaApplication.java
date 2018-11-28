package br.com.cartorio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.cartorio"})
public class CartorioSamambaiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartorioSamambaiaApplication.class, args);
	}
}
