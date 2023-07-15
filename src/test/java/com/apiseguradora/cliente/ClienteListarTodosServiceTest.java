package com.apiseguradora.cliente;


import com.apiseguradora.model.Cliente;
import com.apiseguradora.repository.ClienteRepository;
import com.apiseguradora.service.Cliente.ClienteListarTodosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClienteListarTodosServiceTest {

    @InjectMocks
    ClienteListarTodosService clienteListarTodosService;

    @Mock
    ClienteRepository clienteRepository;

    private static final Logger logger = LoggerFactory.getLogger(ClienteListarTodosServiceTest.class);


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListarClientes() {
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();

        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

        ResponseEntity<Object> response = clienteListarTodosService.listarClientes();

        logger.info("Response: {}", response);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Arrays.asList(cliente1, cliente2), response.getBody());
    }

    @Test
    public void testListarClientesEmpty() {
        when(clienteRepository.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<Object> response = clienteListarTodosService.listarClientes();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        // Also test the ApiMessage content if possible
    }

    @Test
    public void testListarClientesError() {
        when(clienteRepository.findAll()).thenThrow(new RuntimeException());

        ResponseEntity<Object> response = clienteListarTodosService.listarClientes();
        logger.info("Response: {}", response);


        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
