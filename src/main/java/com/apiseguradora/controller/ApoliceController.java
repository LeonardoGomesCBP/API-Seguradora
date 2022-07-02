package com.apiseguradora.controller;
import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiseguradora.model.Apolice;
import com.apiseguradora.model.Cliente;
import com.apiseguradora.repository.ApoliceRepository;
import com.apiseguradora.service.ApoliceAtualizarService;
import com.apiseguradora.service.ApoliceBuscarPorNumero;
import com.apiseguradora.service.ApoliceCadastroService;
import com.apiseguradora.service.ApoliceDeletarService;
import com.apiseguradora.service.ApoliceListarTodosService;
import com.apiseguradora.service.ApoliceReturnService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/apolice")
@Api
@CrossOrigin(origins="*")
public class ApoliceController {

	@Autowired
	ApoliceRepository apoliceRepository;
	
	@Autowired
	ApoliceCadastroService apoliceCadastroService;
	
	@Autowired
	ApoliceAtualizarService apoliceAtualizarService;

	@Autowired
	ApoliceBuscarPorNumero apoliceBuscarPorNumero;
	
	@Autowired 
	ApoliceDeletarService apoliceDeletarService;
	
	@Autowired ApoliceListarTodosService apoliceListarTodosService;
		
	@ApiOperation(value="Cria uma apolice")
	@PostMapping("/criar")
	public ResponseEntity<Object> criarApolice(@RequestBody @NotBlank @Valid Apolice apolice) {
		return apoliceCadastroService.cadastro(apolice);
		
	}

	@ApiOperation(value="Busca uma apolice por número da apolice")
	@GetMapping("/numeroApolice/{numeroApolice}")
	public ResponseEntity<Object> buscarApolicePorNumero(@NotBlank @PathVariable("numeroApolice") String numeroApolice) throws ParseException {
		return apoliceBuscarPorNumero.buscarApolicePorNumero(numeroApolice);
		
	}



	@ApiOperation(value="Atualiza apolice por id")
	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizarApolice(@PathVariable("id") Long id, @RequestBody Apolice apolice) {
		return apoliceAtualizarService.AtualizarApolicePorID(id, apolice);
		
	}
	
	@ApiOperation(value="Deleta apolice por id")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletarApolice(@PathVariable("id") Long id) {
		return apoliceDeletarService.deletarApolice(id);
	}

	
	@GetMapping("/listar")
	public ResponseEntity<Object> listarApolices() {
		return apoliceListarTodosService.listarApolices();
	}




}
