package com.apiseguradora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apiseguradora.exception.ApiMessage;
import com.apiseguradora.model.Cliente;
import com.apiseguradora.repository.ClienteRepository;


@Service
public class ClienteAtualizarService {
	@Autowired
	ClienteRepository clienteRepository;
	
	public ResponseEntity<Object> atualizarCliente (Long id, Cliente Cliente) {
		Optional<Cliente> clienteBanco = clienteRepository.findById(id);

		if (clienteBanco.isPresent()) {
			Cliente _cliente = clienteBanco.get();
			_cliente.setNomeCompleto(Cliente.getNomeCompleto());
			_cliente.setCidade(Cliente.getCidade());
			_cliente.setCpf(Cliente.getCpf());
			_cliente.setUf(Cliente.getUf());

			return new ResponseEntity<>(clienteRepository.save(_cliente), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ApiMessage("Cliente não encontrado"), HttpStatus.NOT_FOUND);
		}
	}
}
