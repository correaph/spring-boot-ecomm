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

import br.com.phmr.springbootecomm.domain.Cliente;
import br.com.phmr.springbootecomm.dto.ClienteDTO;
import br.com.phmr.springbootecomm.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElse(null);
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = repo.findById(obj.getId()).orElse(null);
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		repo.deleteById(id);
		return;
	}

	public List<ClienteDTO> findAll() {
		List<Cliente> list = repo.findAll();
		List<ClienteDTO> listDTO = new ArrayList<>();
		listDTO = list.stream().map(item -> new ClienteDTO(item)).collect(Collectors.toList());
		return listDTO;
	}

	public Page<ClienteDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Cliente> list = repo.findAll(pageRequest);
		Page<ClienteDTO> listDTO = list.map(item -> new ClienteDTO(item));
		return listDTO;
	}

	public Cliente fromDTO(ClienteDTO obj) {
		return new Cliente(obj.getId(), obj.getNome(), obj.getEmail());
	}

}
