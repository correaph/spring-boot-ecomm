package br.com.phmr.springbootecomm.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phmr.springbootecomm.domain.Categoria;
import br.com.phmr.springbootecomm.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		repo.deleteById(id);
		return; 
	}

}
