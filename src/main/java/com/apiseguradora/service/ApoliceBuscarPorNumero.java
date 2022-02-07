package com.apiseguradora.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.apiseguradora.exception.ApiMessage;
import com.apiseguradora.model.Apolice;
import com.apiseguradora.repository.ApoliceRepository;

@Service
public class ApoliceBuscarPorNumero {

	@Autowired
	ApoliceRepository apoliceRepository;

	public ResponseEntity<Object> buscarApolicePorNumero(String numeroApolice) {
		Apolice Apolice = apoliceRepository.buscarApolicePorNumero(numeroApolice);

		ApoliceReturnService apoVO = new ApoliceReturnService(Apolice);
		if (Apolice != null) {
			return new ResponseEntity<>(apoVO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ApiMessage("Apolice não encontrada:"), HttpStatus.NOT_FOUND);

		}

	}
}
