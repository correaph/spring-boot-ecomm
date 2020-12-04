package br.com.phmr.springbootecomm.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.phmr.springbootecomm.domain.Cliente;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 8141460624096355178L;

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatório (Nome Cliente)")
	@Length(min = 5, max = 70, message = "Quantidade de caracteres deve estar entre 5 e 70 (Nome Cliente)")
	private String nome;

	@Email(message = "Email inválido")
	private String email;

	public ClienteDTO() {
	}
	
	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}