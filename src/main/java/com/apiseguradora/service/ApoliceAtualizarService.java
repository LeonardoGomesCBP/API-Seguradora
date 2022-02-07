package com.apiseguradora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apiseguradora.exception.ApiMessage;
import com.apiseguradora.model.Apolice;
import com.apiseguradora.repository.ApoliceRepository;

@Service
public class ApoliceAtualizarService {

	@Autowired
	ApoliceRepository apoliceRepository;
	
	public ResponseEntity<Object> AtualizarApolicePorID(Long id, Apolice apolice) {

		Optional<Apolice> apoliceBanco = apoliceRepository.findById(id);

		if (apoliceBanco.isPresent()) {
			Apolice _apolice = apoliceBanco.get();

			if (!" ".equals(apolice.getNumeroApolice())) {
				_apolice.setNumeroApolice(apolice.getNumeroApolice());
			}
			_apolice.setPlacaVeiculo(apolice.getPlacaVeiculo());
			_apolice.setValorApolice(apolice.getValorApolice());
			_apolice.setDataInicioVigencia(apolice.getDataInicioVigencia());
			_apolice.setDataFimVigencia(apolice.getDataFimVigencia());

			return new ResponseEntity<>(apoliceRepository.save(_apolice), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ApiMessage("Apolice não existente:" + id), HttpStatus.NOT_FOUND);
		}
	}
}