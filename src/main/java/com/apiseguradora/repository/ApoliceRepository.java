package com.apiseguradora.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apiseguradora.model.Apolice;

@Repository
public interface ApoliceRepository extends JpaRepository<Apolice, Long>{

	@Query("SELECT a.numeroApolice from Apolice a")
	public ArrayList<String> listarApolices();

	@Query("SELECT a from Apolice a where a.numeroApolice = ?1")
	public Apolice buscarApolicePorNumero(String numeroApolice);


	


	

}
