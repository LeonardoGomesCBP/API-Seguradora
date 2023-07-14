package com.apiseguradora.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.apiseguradora.model.Cliente;
import com.apiseguradora.repository.ClienteRepository;
import com.apiseguradora.service.Cliente.ClienteAtualizarService;
import com.apiseguradora.service.Cliente.ClienteBuscarPorIDService;
import com.apiseguradora.service.Cliente.ClienteCadastroService;
import com.apiseguradora.service.Cliente.ClienteDeletarService;
import com.apiseguradora.service.Cliente.ClienteListarTodosService;


@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {


	@Autowired
	ClienteRepository cliRepository;
	@Autowired
	ClienteListarTodosService clienteListarTodosService;
	@Autowired
	ClienteBuscarPorIDService clienteBuscarPorIDService;
	@Autowired
	ClienteCadastroService clienteCadastroService;
	@Autowired
	ClienteAtualizarService clienteAtualizarService;
	@Autowired
	ClienteDeletarService clienteDeletarService;
	
	@PostMapping("/novo")
	public ResponseEntity<?> criarCliente(@RequestBody @Valid Cliente Cliente) {
		return clienteCadastroService.criarCliente(Cliente);


	}

	@GetMapping("/listar")
	public ResponseEntity<Object> listarClientes() {
		return clienteListarTodosService.listarClientes();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getClienteById(@PathVariable("id") Long id  )  {
		return clienteBuscarPorIDService.buscarPorId(id);

	}

	

	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizarCliente(@PathVariable("id") Long id, @Valid @RequestBody Cliente Cliente)   {
		return clienteAtualizarService.atualizarCliente(id, Cliente);
		
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletarCliente(@PathVariable("id") Long id) {
		return clienteDeletarService.deletarCliente(id);

}
}