package com.apiseguradora.service.Cliente;

import com.apiseguradora.exception.ApiMessage;
import com.apiseguradora.model.Cliente;
import com.apiseguradora.repository.ClienteRepository;
import com.apiseguradora.utils.CpfValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClienteCadastroService {
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    CpfValidator cpfValidator;

    public ResponseEntity<Object> criarCliente(Cliente cliente) {
        String cpf = cliente.getCpf().replaceAll("[^0-9]", "");

        if (clienteRepository.buscarPorCPF(cpf) != 0) {
            return new ResponseEntity<>(new ApiMessage("CPF já cadastrado!"), HttpStatus.PRECONDITION_REQUIRED);
        }

        if (!cpfValidator.isValid(cpf)) {
            return new ResponseEntity<>(new ApiMessage("CPF não é válido!"), HttpStatus.PRECONDITION_REQUIRED);
        }

        try {
            cliente.setCpf(cpf);
            Cliente _cliente = clienteRepository.save(cliente);
            return new ResponseEntity<>(_cliente, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    public void setCpfValidator(CpfValidator cpfValidator) {
        this.cpfValidator = cpfValidator;
    }

    public void setClienteRepository(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

}