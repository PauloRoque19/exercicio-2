/**
 * 
 */
package br.com.cbgomes.acme.client.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cbgomes.acme.client.domain.Client;
import br.com.cbgomes.acme.client.repository.ClientRepository;
import br.com.cbgomes.acme.exception.AplicationException;

/**
 * @author cbgomes
 *
 */
@RestController
@RequestMapping("/api/clients")
public class ClientResource {
	
	@Autowired
	private ClientRepository repository;
	

	
	@GetMapping
	public List<Client> getAllClients(){
		List<Client> clients  = this.repository.findAll();
				
		if(clients == null || clients.isEmpty())
			throw new AplicationException("Lista de Clientes vazia");
		return clients;
	}
	
	@PostMapping
	public void createClient(@RequestBody Client client) {
		if(client.getName().isEmpty()) {
			this.repository.save(client);
		}
		else {
			throw new AplicationException("Cadastro não efetuado");
		}
			
	}
	
	@PutMapping
	public void updateCliente(@RequestBody Client client) {
		if(client.getId() != null) {
			this.repository.saveAndFlush(client);
		}else {
			throw new AplicationException("Update não efetuado");
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteClient(@PathVariable Long id) {
		Client c = repository.findById(id).orElseThrow(() -> new AplicationException("Cliente não pode ser deletado pois ele não existe."));	 
			
	}
	
	@GetMapping("/{id}")
	public Client getById(@PathVariable Long id) throws RuntimeException{
		return repository.findById(id).orElseThrow(() -> new AplicationException("Cliente não encontrado."));		
	}
	
}











