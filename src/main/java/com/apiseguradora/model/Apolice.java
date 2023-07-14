package com.apiseguradora.model;



import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
@Entity
public class Apolice extends AbstractEntity<Long> {


//	@NotNull(message = "{dataFimVigencia.not.blank}")
	private LocalDate dataFimVigencia;

   // @NotNull(message = "{dataInicioVigencia.not.blank}")
	private LocalDate dataInicioVigencia;

	@Column(unique = true)
   // @NotBlank(message = "{numeroApolice.not.blank}")
	private String numeroApolice;

    //@NotBlank(message = "{placaVeiculo.not.blank}")
	private String placaVeiculo;

   // @NotNull(message = "{valorApolice.not.blank}")
	private Long valorApolice;


	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne(optional = false)
	@JoinColumn(name = "cliente_id_fk")
	private Cliente cliente;


	public Apolice() {
	}







}
