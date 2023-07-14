package com.apiseguradora.service.Cliente;

import com.apiseguradora.exception.ApiMessage;
import com.apiseguradora.model.Cliente;
import com.apiseguradora.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ClienteBuscarPorIDService {

    @Autowired
    ClienteRepository clienteRepository;

    public ResponseEntity<Object> buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isPresent()) {
            return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiMessage("Cliente não encontrado:" + id), HttpStatus.NOT_FOUND);
        }
    }
}
