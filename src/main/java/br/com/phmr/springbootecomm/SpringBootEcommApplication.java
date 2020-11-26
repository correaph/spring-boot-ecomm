package br.com.phmr.springbootecomm;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.phmr.springbootecomm.domain.Categoria;
import br.com.phmr.springbootecomm.repositories.CategoriaRepository;

@SpringBootApplication
public class SpringBootEcommApplication implements CommandLineRunner{

	@Autowired
	CategoriaRepository categoriaRepository; 
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootEcommApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var cat1 = new Categoria(null,"Informática");
		var cat2 = new Categoria(null,"Escritório");
		var cat3 = new Categoria(null,"Teste");
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
	}

}
