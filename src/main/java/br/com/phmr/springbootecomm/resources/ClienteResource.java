package br.com.phmr.springbootecomm.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.phmr.springbootecomm.domain.Cliente;
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

}
