package com.apiseguradora.service;

import com.apiseguradora.model.Apolice;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@Service
public class ApoliceReturnService {


    String numeroApolice;


    Boolean apoliceVencida;
    int diasVencer;
    String placaVeiculo;
    Long valorApolice;
    LocalDate inicio;
    LocalDate fim;


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

    public ApoliceReturnService() {

    }

}
