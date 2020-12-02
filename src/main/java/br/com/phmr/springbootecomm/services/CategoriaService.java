package br.com.phmr.springbootecomm.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.phmr.springbootecomm.domain.Categoria;
import br.com.phmr.springbootecomm.dto.CategoriaDTO;
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

	public List<CategoriaDTO> findAll() {
		List<Categoria> list = repo.findAll();
		List<CategoriaDTO> listDTO = new ArrayList<>();
		listDTO = list.stream().map(item -> new CategoriaDTO(item)).collect(Collectors.toList());
		return listDTO;
	}

	public Page<CategoriaDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Categoria> list = repo.findAll(pageRequest);
		Page<CategoriaDTO> listDTO = list.map(item -> new CategoriaDTO(item));
		return listDTO;
	}

}
