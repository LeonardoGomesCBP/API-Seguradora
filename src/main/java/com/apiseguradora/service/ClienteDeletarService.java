package com.apiseguradora.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apiseguradora.exception.ApiMessage;
import com.apiseguradora.repository.ClienteRepository;


@Service
public class ClienteDeletarService {

	@Autowired
	ClienteRepository clienteRepository;
	public ResponseEntity<Object> deletarCliente(Long id) {
		try {
			clienteRepository.deleteById(id);
			return new ResponseEntity<>(new ApiMessage("Cliente deletado:" + id), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(new ApiMessage("Cliente não encontrado: " +  id), HttpStatus.NOT_FOUND);
		}
	}
}
