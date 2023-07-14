package com.apiseguradora.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Entity
public class Cliente extends AbstractEntity<Long> {

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

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private List<Apolice> apolice;


	public Cliente() {
	}


}
