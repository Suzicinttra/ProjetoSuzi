package com.app.desafio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.desafio.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	public Optional<Cliente> findByEmail(String email);
	public Optional<Cliente> findByUsuario(String usuario); 
	public Optional<Cliente> findById(Long id);
	

}
