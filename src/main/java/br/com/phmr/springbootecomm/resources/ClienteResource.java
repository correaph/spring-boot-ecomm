package br.com.phmr.springbootecomm.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.phmr.springbootecomm.domain.Cliente;
import br.com.phmr.springbootecomm.dto.ClienteDTO;
import br.com.phmr.springbootecomm.dto.ClienteNewDTO;
import br.com.phmr.springbootecomm.resources.exceptions.StandardError;
import br.com.phmr.springbootecomm.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Cliente obj = service.find(id);
		if (obj == null) {
			return ResponseEntity.status(404).body(new StandardError("Cliente não encontrado. Id:" + id));
		}
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDTO) {
		var newObj = service.insert(service.fromDTO(objDTO));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id) {
		find(id);
		objDTO.setId(id);
		service.update(service.fromDTO(objDTO));
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		find(id);
		try {
			service.delete(id);
			return ResponseEntity.noContent().build();
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.badRequest()
					.body(new StandardError("Cliente não pode ser excluído pois possui pedidos associados."));
		}
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<?> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<ClienteDTO> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<ClienteDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

}
