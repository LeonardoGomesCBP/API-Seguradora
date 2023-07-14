package com.apiseguradora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apiseguradora.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	@Query("Select count(c) from Cliente c where c.cpf = ?1") 
	Integer buscarPorCPF(String cpf);

	Cliente findByCpf(String cpf);


}
