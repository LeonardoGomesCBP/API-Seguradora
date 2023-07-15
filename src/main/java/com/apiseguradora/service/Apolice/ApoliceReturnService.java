package com.apiseguradora.service.Apolice;

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
    public ApoliceReturnService(Apolice apolice) {
        this.numeroApolice = apolice.getNumeroApolice();
        this.inicio = apolice.getDataInicioVigencia();
        this.fim = apolice.getDataFimVigencia();
        this.placaVeiculo = apolice.getPlacaVeiculo();
        this.valorApolice = apolice.getValorApolice();

        this.diasVencer = (int) ChronoUnit.DAYS.between(inicio, fim);
        this.apoliceVencida = diasVencer < 0;
    }


    public ApoliceReturnService() {

    }

}
