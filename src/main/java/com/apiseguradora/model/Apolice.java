package com.apiseguradora.model;



import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "apolice")
public class Apolice implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private Long id;

	@Column(name = "data_fim_vigencia")
    @NotBlank
	private String dataFimVigencia;

	@Column(name = "data_inicio_vigencia")
    @NotBlank
	private String dataInicioVigencia;

	@Column(name = "numero_apolice", unique = true)
    @NotBlank
	private String numeroApolice;

	@Column(name = "placa_veiculo")
    @NotBlank
	private String placaVeiculo;

	@Column(name = "valor_apolice")
    @NotBlank
	private String valorApolice;
	


	


}
