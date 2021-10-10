package com.app.desafio.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.desafio.models.Cliente;
import com.app.desafio.models.ClienteLogin;
import com.app.desafio.repository.ClienteRepository;
import com.app.desafio.service.ClienteService;

@RestController
@CrossOrigin("*")
public class ClienteController {
	
	@Autowired private ClienteService clienteService;
	@Autowired private ClienteRepository clienteRepository;
	
	@PostMapping("/cliente/cadastro")
	public ResponseEntity<?> cadastrarCliente (@Valid @RequestBody Cliente novoCliente){
		Cliente clienteCadastrado = clienteService.cadastrarCliente(novoCliente);
		if (clienteCadastrado == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email já cadastrado");
		}
		return new ResponseEntity<Cliente>(clienteService.cadastrarCliente(novoCliente), HttpStatus.CREATED);
	}
	
	
	@PostMapping("/cliente/login")
	public ResponseEntity<ClienteLogin> auth(@RequestBody Optional<ClienteLogin> clienteLogin){
		return clienteService.logarCliente(clienteLogin).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@GetMapping("/clientes")
	public ResponseEntity<List<Cliente>> pegarTodes() {
		List<Cliente> todosClientes = clienteRepository.findAll();
		if (todosClientes.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(todosClientes);
		}
	}
	
	
	@DeleteMapping("cliente/exclusao/{id_cliente}")
	public ResponseEntity<Object> deletarCliente(@PathVariable(value = "id_cliente") Long idCliente) {
		Optional<Cliente> cliente = clienteRepository.findById(idCliente);
		if (cliente.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id de cliente inválido");
		} else {
			clienteRepository.deleteById(idCliente);
			return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado");
		}
	}
	
	
	
	
	
}
