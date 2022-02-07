package com.apiseguradora.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apiseguradora.exception.ApiMessage;
import com.apiseguradora.model.Apolice;
import com.apiseguradora.repository.ApoliceRepository;
import com.apiseguradora.utils.Utils;

@Service
public class ApoliceCadastroService {
	
	
	@Autowired
	ApoliceRepository apoliceRepository;

	public ResponseEntity<Object> cadastro(Apolice apolice) {
	 
	ArrayList<String> listaApolices = apoliceRepository.listarApolices();

	Apolice apoliceNovo = new Apolice();

	apoliceNovo.setNumeroApolice(Utils.randomNovo(listaApolices));  
	apoliceNovo.setPlacaVeiculo(apolice.getPlacaVeiculo());
	apoliceNovo.setValorApolice(apolice.getValorApolice());
	apoliceNovo.setDataInicioVigencia(apolice.getDataInicioVigencia());
	apoliceNovo.setDataFimVigencia(apolice.getDataFimVigencia());

	try {
		Apolice Apolicee = apoliceRepository.save(apoliceNovo);
		return new ResponseEntity<>(Apolicee, HttpStatus.CREATED);
	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
}
