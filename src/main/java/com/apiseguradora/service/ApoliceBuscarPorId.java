package com.apiseguradora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apiseguradora.exception.ApiMessage;
import com.apiseguradora.model.Apolice;
import com.apiseguradora.model.Cliente;
import com.apiseguradora.repository.ApoliceRepository;
@Service
public class ApoliceBuscarPorId {
	
	@Autowired
	ApoliceRepository apoliceRepository;

	public ResponseEntity<Object> buscarPorId(Long id) {
		Optional<Apolice> apolice = apoliceRepository.findById(id); 

		if (apolice.isPresent()) {
			return new ResponseEntity<>(apolice.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ApiMessage("Apolice não encontrado:" + id), HttpStatus.NOT_FOUND);
		}
	}
}
