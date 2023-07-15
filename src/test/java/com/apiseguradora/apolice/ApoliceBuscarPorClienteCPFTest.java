package com.apiseguradora.apolice;

import com.apiseguradora.cliente.ClienteBuscarPorIDServiceTest;
import com.apiseguradora.model.Apolice;
import com.apiseguradora.model.Cliente;
import com.apiseguradora.repository.ApoliceRepository;
import com.apiseguradora.repository.ClienteRepository;
import com.apiseguradora.service.Apolice.ApolicaBuscarPorClienteCPF;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ApoliceBuscarPorClienteCPFTest {

    @InjectMocks
    ApolicaBuscarPorClienteCPF apolicaBuscarPorClienteCPF;

    @Mock
    ApoliceRepository apoliceRepository;

    @Mock
    ClienteRepository clienteRepository;

    private static final Logger logger = LoggerFactory.getLogger(ApoliceBuscarPorClienteCPFTest.class);


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetApolicesByClienteCpf() {
        String cpf = "12345678901";
        Cliente cliente = new Cliente();
        Apolice apolice1 = new Apolice();
        Apolice apolice2 = new Apolice();

        when(clienteRepository.findByCpf(cpf)).thenReturn(cliente);
        when(apoliceRepository.findByCliente(cliente)).thenReturn(Arrays.asList(apolice1, apolice2));

        ResponseEntity<Object> response = apolicaBuscarPorClienteCPF.getApolicesByClienteCpf(cpf);
        logger.info("Response: {}", response);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Arrays.asList(apolice1, apolice2), response.getBody());
    }

    @Test
    public void testGetApolicesByClienteCpfClienteNotFound() {
        String cpf = "12345678901";

        when(clienteRepository.findByCpf(cpf)).thenReturn(null);

        ResponseEntity<Object> response = apolicaBuscarPorClienteCPF.getApolicesByClienteCpf(cpf);
        logger.info("Response: {}", response);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetApolicesByClienteCpfError() {
        String cpf = "12345678901";

        when(clienteRepository.findByCpf(cpf)).thenThrow(new RuntimeException());

        ResponseEntity<Object> response = apolicaBuscarPorClienteCPF.getApolicesByClienteCpf(cpf);
        logger.info("Response: {}", response);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
