package br.com.phmr.springbootecomm.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phmr.springbootecomm.domain.Pedido;
import br.com.phmr.springbootecomm.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElse(null);
	}

}
