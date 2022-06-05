package com.apiseguradora.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apiseguradora.exception.ApiMessage;
import com.apiseguradora.model.Cliente;
import com.apiseguradora.repository.ClienteRepository;
import com.apiseguradora.utils.Utils;

@Service
public class ClienteCadastroService {
	@Autowired
	ClienteRepository clienteRepository;
	
	Cliente cli = new Cliente();

	
	public ResponseEntity<Object> criarCliente(Cliente Cliente) {

		int existeCadastro = 0;

		existeCadastro = clienteRepository.buscarPorCPF(Cliente.getCpf().replaceAll("[^0-9]", "")); // o mesmo que o TRIM

		if (existeCadastro != 0) {
			return new ResponseEntity<>("CPF já cadastrado!", HttpStatus.PRECONDITION_REQUIRED); 
		} else {
			cli.setNomeCompleto(Cliente.getNomeCompleto());
			cli.setCidade(Cliente.getCidade());
			cli.setCpf(Cliente.getCpf().replaceAll("[^0-9]", ""));
			cli.setUf(Cliente.getUf());

			try {
				if (Utils.isValidCPF(Cliente.getCpf().replaceAll("[^0-9]", ""))) {
					Cliente _cliente = clienteRepository.save(cli);
					return new ResponseEntity<>(_cliente, HttpStatus.CREATED);
				} else {
					return new ResponseEntity<>("CPF não é válido!", HttpStatus.PRECONDITION_REQUIRED);
				}
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

		}
		}
}
