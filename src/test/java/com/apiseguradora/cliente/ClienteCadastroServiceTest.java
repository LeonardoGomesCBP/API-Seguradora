package com.apiseguradora.cliente;

import com.apiseguradora.model.Cliente;
import com.apiseguradora.repository.ClienteRepository;
import com.apiseguradora.service.Cliente.ClienteCadastroService;
import com.apiseguradora.utils.CpfValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
public class ClienteCadastroServiceTest {

    @MockBean
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteCadastroService clienteCadastroService;


    @MockBean
    private CpfValidator cpfValidator;

    @BeforeEach
    public void setUp() {
        clienteCadastroService.setClienteRepository(clienteRepository);
        clienteCadastroService.setCpfValidator(cpfValidator);

    }

    @Test
    public void testCriarCliente_Success() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678901");

        when(cpfValidator.isValid(anyString())).thenReturn(true);
        when(clienteRepository.buscarPorCPF(anyString())).thenReturn(0);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        ResponseEntity<Object> result = clienteCadastroService.criarCliente(cliente);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }


    @Test
    public void testCriarCliente_CpfJaCadastrado() {
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678901");

        when(clienteRepository.buscarPorCPF(anyString())).thenReturn(1);

        ResponseEntity<Object> result = clienteCadastroService.criarCliente(cliente);

        assertEquals(HttpStatus.PRECONDITION_REQUIRED, result.getStatusCode());
    }

    @Test
    public void testCriarCliente_CpfInvalido() {
        Cliente cliente = new Cliente();
        cliente.setCpf("231234123124");

        when(clienteRepository.buscarPorCPF(anyString())).thenReturn(0);
        when(cpfValidator.isValid(anyString())).thenReturn(true);


        ResponseEntity<Object> result = clienteCadastroService.criarCliente(cliente);

        assertEquals(HttpStatus.PRECONDITION_REQUIRED, result.getStatusCode());
    }


}