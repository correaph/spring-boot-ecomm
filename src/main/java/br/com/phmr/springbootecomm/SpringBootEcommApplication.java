package br.com.phmr.springbootecomm;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.phmr.springbootecomm.domain.Categoria;
import br.com.phmr.springbootecomm.domain.Produto;
import br.com.phmr.springbootecomm.repositories.CategoriaRepository;
import br.com.phmr.springbootecomm.repositories.ProdutoRepository;

@SpringBootApplication
public class SpringBootEcommApplication implements CommandLineRunner{

	@Autowired
	CategoriaRepository categoriaRepository; 
	@Autowired
	ProdutoRepository produtoRepository; 
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootEcommApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		var cat1 = new Categoria(null,"Informática");
		var cat2 = new Categoria(null,"Escritório");
		var cat3 = new Categoria(null,"Teste");

		var p1 = new Produto(null, "Computador", 2500.00);
		var p2 = new Produto(null, "Impressora", 740.98);
		var p3 = new Produto(null, "Mouse", 27.40);
		var p4 = new Produto(null, "Prod. Teste", 27.40);
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4));
		
	
	}

}
