package com.apiseguradora.service.Apolice;

import com.apiseguradora.exception.ApiMessage;
import com.apiseguradora.model.Apolice;
import com.apiseguradora.model.Cliente;
import com.apiseguradora.repository.ApoliceRepository;
import com.apiseguradora.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApolicaBuscarPorClienteCPF{

    @Autowired
    private ApoliceRepository apoliceRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseEntity<Object> getApolicesByClienteCpf(String cpf) {
        try {
            Cliente cliente = clienteRepository.findByCpf(cpf);
            if (cliente == null) {
                return new ResponseEntity<>(new ApiMessage("Cliente não encontrado"), HttpStatus.NOT_FOUND);
            }
            List<Apolice> apolices = apoliceRepository.findByCliente(cliente);
            return new ResponseEntity<>(apolices, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiMessage("Algo inesperado ocorreu: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}