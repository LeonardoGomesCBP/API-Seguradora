package com.apiseguradora.controller;
import java.text.ParseException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.apiseguradora.service.Apolice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apiseguradora.model.Apolice;
import com.apiseguradora.repository.ApoliceRepository;

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

	@Autowired
	ApolicaBuscarPorClienteCPF apoliceBuscarPorClienteCPF;
		
	@ApiOperation(value="Cria uma apolice")
	@PostMapping
	public ResponseEntity<Object> criarApolice(@RequestBody @NotBlank @Valid Apolice apolice) {
		return apoliceCadastroService.cadastro(apolice);
	}

	@ApiOperation(value="Busca uma apolice por número da apolice")
	@GetMapping("/{numeroApolice}")
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

	@ApiOperation(value="Busca por todas as apolices ou por cpf do cliente")
	@GetMapping
	public ResponseEntity<Object> getApolices(@RequestParam(required = false) String cpf) {
		if (cpf != null) {
			return apoliceBuscarPorClienteCPF.getApolicesByClienteCpf(cpf);
		} else {
			return apoliceListarTodosService.listarApolices();
		}
	}





}
