package br.com.phmr.springbootecomm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.phmr.springbootecomm.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> { 

}
