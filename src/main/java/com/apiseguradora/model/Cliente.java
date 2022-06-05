package com.apiseguradora.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "CLIENTE")
public class Cliente {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@NotBlank(message = "{cidade.not.blank}")
	@Column(name = "cidade")
	private String cidade;

	@NotBlank(message = "{cpf.not.blank}")
	@Column(name = "cpf", unique = true)
	private String cpf;

	@NotBlank(message = "{nomeCompleto.not.blank}")
	@Column(name = "nome_completo")
	private String nomeCompleto;

	@NotBlank(message = "{uf.not.blank}")
	@Column(name = "uf")
	private String uf;

	public Cliente() {
	}


}
