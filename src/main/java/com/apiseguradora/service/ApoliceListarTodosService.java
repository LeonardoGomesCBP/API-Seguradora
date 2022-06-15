package com.apiseguradora.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apiseguradora.model.Apolice;
import com.apiseguradora.repository.ApoliceRepository;


@Service
public class ApoliceListarTodosService {

	
	@Autowired
	ApoliceRepository apoliceRepository;
	
	public ResponseEntity<List<Apolice>> listarApolices() {
		try {
			List<Apolice> cli2 = new ArrayList<Apolice>();

			apoliceRepository.findAll().forEach(cli2::add);

			if (cli2.isEmpty()) {
				
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(cli2, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

}
}
