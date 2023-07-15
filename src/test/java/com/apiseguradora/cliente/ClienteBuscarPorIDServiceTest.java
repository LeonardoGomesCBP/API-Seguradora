package com.apiseguradora.cliente;

import com.apiseguradora.model.Cliente;
import com.apiseguradora.repository.ClienteRepository;
import com.apiseguradora.service.Cliente.ClienteBuscarPorIDService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClienteBuscarPorIDServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(ClienteBuscarPorIDServiceTest.class);


    @InjectMocks
    ClienteBuscarPorIDService clienteBuscarPorIDService;

    @Mock
    ClienteRepository clienteRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBuscarPorId() {
        Long id = 1L;
        Cliente cliente = new Cliente();
        cliente.setId(id);

        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        ResponseEntity<Object> response = clienteBuscarPorIDService.buscarPorId(id);

        logger.info("Response: {}", response);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cliente, response.getBody());
    }

    @Test
    public void testBuscarPorIdNotFound() {
        Long id = 1L;

        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = clienteBuscarPorIDService.buscarPorId(id);
        logger.info("Response: {}", response);


        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        // Also test the error message if possible, depending on the implementation of ApiMessage
    }
}
