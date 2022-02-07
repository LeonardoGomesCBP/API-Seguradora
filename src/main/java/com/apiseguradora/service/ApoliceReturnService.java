package com.apiseguradora.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.apiseguradora.model.Apolice;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Service
public class ApoliceReturnService {


	String numeroApolice;

	
	Boolean apoliceVencida;


	int diasVencer;

	
	String placaVeiculo;

	
	String valorApolice;

	
	public ApoliceReturnService() {
	}

	// Lógica para determinar se a apolice está vencida
	public ApoliceReturnService(Apolice Apolice) {

		int diasVencido = 0;
		this.apoliceVencida = false;
		this.numeroApolice = Apolice.getNumeroApolice();

		try {
			diasVencido = this.calculaDatas(this.convertDate(Apolice.getDataFimVigencia()));
			this.diasVencer = diasVencido;
		} catch (ParseException e) {
			return;
		}

		if (diasVencido < 0) {
			this.apoliceVencida = true;
		}
		this.placaVeiculo = Apolice.getPlacaVeiculo();
		this.valorApolice = Apolice.getValorApolice();

	}

	private Date convertDate(String sData) {
		Date date1;
		try {
			date1 = new SimpleDateFormat("dd-MM-yyyy").parse(sData);
			System.out.println(sData + "\t" + date1);

			return date1;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	 // Calcular diferença de dias entre duas datas (data de vencimento e data atual)

	private int calculaDatas(Date dataVencimento) throws ParseException {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDateTime now = LocalDateTime.now();

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date hoje = formatter.parse(dtf.format(now));

		long diff = dataVencimento.getTime() - hoje.getTime();
		return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	
	
	
}
