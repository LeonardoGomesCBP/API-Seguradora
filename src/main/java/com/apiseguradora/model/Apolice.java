package com.apiseguradora.model;



import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
@Entity
@Table(name = "apolice")
public class Apolice implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private Long id;

	@Column(name = "data_fim_vigencia")
	@NotNull(message = "{dataFimVigencia.not.blank}")
	private LocalDate dataFimVigencia;

	@Column(name = "data_inicio_vigencia")
    @NotNull(message = "{dataInicioVigencia.not.blank}")
	private LocalDate dataInicioVigencia;

	@Column(name = "numero_apolice", unique = true)
    @NotBlank(message = "{numeroApolice.not.blank}")
	private String numeroApolice;

	@Column(name = "placa_veiculo")
    @NotBlank
    @NotBlank(message = "{placaVeiculo.not.blank}")
	private String placaVeiculo;

	@Column(name = "valor_apolice")
    @NotNull(message = "{valorApolice.not.blank}")
	private Long valorApolice;
	
	
	
	public Apolice() {
	}



	


}
