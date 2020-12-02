package br.com.phmr.springbootecomm.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.phmr.springbootecomm.domain.Categoria;

public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = -3208643329587479589L;

	private Integer id;

	@NotEmpty(message = "Preencimento obrigatório (Nome Categoria)")
	@Length(min = 5, max = 25, message = "Quantidade de caracteres deve estar entre 5 e 25 (Nome Categoria)")
	private String nome;

	public CategoriaDTO() {
	}

	public CategoriaDTO(Categoria obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
