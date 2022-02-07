package com.apiseguradora.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apiseguradora.exception.ApiMessage;
import com.apiseguradora.model.Cliente;
import com.apiseguradora.repository.ClienteRepository;

@Service
public class ClienteListarTodosService {
	
@Autowired
ClienteRepository clienteRepository;
	
	public ResponseEntity<List<Cliente>> listarClientes() {
		try {
			List<Cliente> cli2 = new ArrayList<Cliente>();

			clienteRepository.findAll().forEach(cli2::add);

			if (cli2.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(cli2, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
}
}