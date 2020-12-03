package br.com.phmr.springbootecomm.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.phmr.springbootecomm.domain.Pedido;
import br.com.phmr.springbootecomm.resources.exceptions.StandardError;
import br.com.phmr.springbootecomm.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Pedido obj = service.find(id);
		if (obj == null) {
			return ResponseEntity.status(404).body(new StandardError("Pedido não encontrado. Id:" + id));
		}
		return ResponseEntity.ok().body(obj);
	}

}
