package com.apiseguradora.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.apiseguradora.exception.ApiMessage;
import com.apiseguradora.repository.ApoliceRepository;

@Service
public class ApoliceDeletarService {

	@Autowired
	ApoliceRepository apoliceRepository;
	
	public ResponseEntity<Object> deletarApolice(Long id) {

	try {
		apoliceRepository.deleteById(id);
		return new ResponseEntity<>(new ApiMessage("Apolice deletada:" + id), HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<>(new ApiMessage("Apolice não encontrada:" + id), HttpStatus.NOT_FOUND);
	
}
}
}