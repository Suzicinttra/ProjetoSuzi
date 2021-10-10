package com.app.desafio.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.app.desafio.models.Cliente;
import com.app.desafio.repository.ClienteRepository;

public class UserDetailsServiceImpl implements UserDetailsService{
	
	private @Autowired ClienteRepository clienteRepository;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Cliente> usuario = clienteRepository.findByUsuario(username);
		usuario.orElseThrow(() -> new UsernameNotFoundException(username + " not found."));

		return usuario.map(UserDetailsImpl::new).get();
	
	}


}
