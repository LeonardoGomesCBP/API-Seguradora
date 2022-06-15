package com.apiseguradora.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;
import org.joda.time.Days;
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
	Long valorApolice;	
	LocalDate inicio;
	LocalDate fim ;

	

	// Lógica para determinar se a apolice está vencida
	public ApoliceReturnService(Apolice Apolice) throws ParseException {

		int diasVencido = 0;
		this.apoliceVencida = false;
		this.numeroApolice = Apolice.getNumeroApolice();
		this.inicio = Apolice.getDataInicioVigencia();
		this.fim = Apolice.getDataFimVigencia();

		


		diasVencido = (int) ChronoUnit.DAYS.between(inicio, fim);
		this.diasVencer = diasVencido;

		if (diasVencido < 0) {
			this.apoliceVencida = true;
		}
		this.placaVeiculo = Apolice.getPlacaVeiculo();
		this.valorApolice = Apolice.getValorApolice();

	}

	public ApoliceReturnService () {
		
	}
	
}
