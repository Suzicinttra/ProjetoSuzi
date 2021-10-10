package com.app.desafio.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.apache.commons.codec.binary.Base64;
import com.app.desafio.models.Cliente;
import com.app.desafio.models.ClienteLogin;
import com.app.desafio.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired private ClienteRepository clienteRepository;
	
	
	public Cliente cadastrarCliente(Cliente novoCliente) {
		Optional<Cliente> usuarioExistente = clienteRepository.findByEmail(novoCliente.getEmail());
		if(usuarioExistente.isPresent()) {
			return null;
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaCriptografada = encoder.encode(novoCliente.getSenha());
		novoCliente.setSenha(senhaCriptografada);
		
		return clienteRepository.save(novoCliente);
	}


	public Optional<ClienteLogin> logarCliente(Optional<ClienteLogin> clienteLogin) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Cliente> clienteLogado = clienteRepository.findByUsuario(clienteLogin.get().getUsuario());

		if(clienteLogado.isPresent()) {
			if(encoder.matches(clienteLogin.get().getSenha(), clienteLogado.get().getSenha())) {
				String credencial = clienteLogin.get().getUsuario() + ":" + clienteLogin.get().getSenha();
				byte[] autorizacaoBase64 = Base64.encodeBase64(credencial.getBytes(Charset.forName("US-ASCII")));
				String autorizacaoHeader = "Basic " + new String(autorizacaoBase64);
				
				clienteLogin.get().setToken(autorizacaoHeader);				
				clienteLogin.get().setNome(clienteLogado.get().getNome());
				clienteLogin.get().setSenha(clienteLogado.get().getSenha());

				return clienteLogin;
			}
		}
		return null;
	}
	
	
	

}
