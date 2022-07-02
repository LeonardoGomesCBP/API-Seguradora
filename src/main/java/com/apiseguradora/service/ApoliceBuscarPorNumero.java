package com.apiseguradora.service;

import com.apiseguradora.exception.ApiMessage;
import com.apiseguradora.model.Apolice;
import com.apiseguradora.repository.ApoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ApoliceBuscarPorNumero {

    @Autowired
    ApoliceRepository apoliceRepository;

    public ResponseEntity<Object> buscarApolicePorNumero(String numeroApolice) {

        try {
            Apolice Apolice = apoliceRepository.buscarApolicePorNumero(numeroApolice);
            ApoliceReturnService apoVO = new ApoliceReturnService(Apolice);
            return new ResponseEntity<>(apoVO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiMessage("Apolice não encontrada:" + numeroApolice), HttpStatus.NOT_FOUND);
        }

    }
}

