package com.apiseguradora.cliente;

import com.apiseguradora.repository.ClienteRepository;
import com.apiseguradora.service.Cliente.ClienteDeletarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
public class ClienteDeletarServiceTest {

    @InjectMocks
    ClienteDeletarService clienteDeletarService;

    @Mock
    ClienteRepository clienteRepository;

    private static final Logger logger = LoggerFactory.getLogger(ClienteDeletarServiceTest.class);


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeletarCliente() {
        Long id = 1L;

        doNothing().when(clienteRepository).deleteById(id);

        ResponseEntity<Object> response = clienteDeletarService.deletarCliente(id);
        logger.info("Response: {}", response);


        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    public void testDeletarClienteNotFound() {
        Long id = 1L;

        doThrow(new RuntimeException()).when(clienteRepository).deleteById(id);

        ResponseEntity<Object> response = clienteDeletarService.deletarCliente(id);
        logger.info("Response: {}", response);


        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
