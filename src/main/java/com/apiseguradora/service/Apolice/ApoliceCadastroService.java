package com.apiseguradora.service.Apolice;

import java.io.Console;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apiseguradora.exception.ApiMessage;
import com.apiseguradora.model.Apolice;
import com.apiseguradora.repository.ApoliceRepository;
import com.apiseguradora.utils.Utils;

@Service
public class ApoliceCadastroService {

	@Autowired
	private ApoliceRepository apoliceRepository;

	public ResponseEntity<Object> cadastro(Apolice apolice) {
		try {
			apoliceRepository.save(apolice);
			return new ResponseEntity<>(new ApiMessage("Apolice cadastrada! Dados: " + apolice), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ApiMessage("Algo inesperado ocorreu: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}