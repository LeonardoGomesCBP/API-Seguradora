package com.apiseguradora.service.Apolice;

import com.apiseguradora.exception.ApiMessage;
import com.apiseguradora.model.Apolice;
import com.apiseguradora.repository.ApoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ApoliceListarTodosService {

	
	@Autowired
	ApoliceRepository apoliceRepository;
	
	public ResponseEntity<Object> listarApolices() {
		try {
			List<Apolice> cli2 = new ArrayList<Apolice>();

			apoliceRepository.findAll().forEach(cli2::add);

			if (cli2.isEmpty()) {
				return new ResponseEntity<>(new ApiMessage("Não existem apolices registradas, lista vazia!"),  HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(cli2, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ApiMessage("Erro desconhecido"), HttpStatus.INTERNAL_SERVER_ERROR);
		}

}
}
