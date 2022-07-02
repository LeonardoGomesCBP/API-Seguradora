package com.apiseguradora.service;

import com.apiseguradora.exception.ApiMessage;
import com.apiseguradora.model.Cliente;
import com.apiseguradora.repository.ClienteRepository;
import com.apiseguradora.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ClienteAtualizarService {
    @Autowired
    ClienteRepository clienteRepository;


    Cliente cli = new Cliente();

    public ResponseEntity<Object> atualizarCliente(Long id, Cliente Cliente) {
        Optional<Cliente> clienteBanco = clienteRepository.findById(id);

        if (clienteBanco.isPresent()) {
            Cliente _cliente = clienteBanco.get();
            _cliente.setNomeCompleto(Cliente.getNomeCompleto());
            _cliente.setCidade(Cliente.getCidade());
            _cliente.setCpf(Cliente.getCpf());
            _cliente.setUf(Cliente.getUf());

            try {
                if (Utils.isValidCPF(Cliente.getCpf().replaceAll("[^0-9]", ""))) {
                    Cliente cliente = clienteRepository.save(cli);
                    return new ResponseEntity<>(_cliente, HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>(new ApiMessage("CPF não é válido!"), HttpStatus.PRECONDITION_REQUIRED);
                }
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity<>(new ApiMessage("Cliente não encontrado:" + id), HttpStatus.NOT_FOUND);
        }
    }
}
