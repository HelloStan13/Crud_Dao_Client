package com.project1.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project1.Exception.ResourceNotFoundException;
import com.project1.Repo.ClientRepo;
import com.project1.Service.SequenceService;
import com.project1.model.Client;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ClientController {
	
	@Autowired
	private ClientRepo clientRepo;
	
	@Autowired
	private SequenceService sequenceService  ;
	
	// this method is used to  retreive all the employees list
	@GetMapping("/clientes")
	public List<Client> getAllEmployees(){
		
		return clientRepo.findAll();
	}
	
	
	// this  handler method is used to retrieve employee by id  
	@GetMapping("/clientes/{id}")
	public ResponseEntity<Client> getEmployeeById(@PathVariable(value = "id") Long clienteId)
			throws ResourceNotFoundException {
		Client client = clientRepo.findById(clienteId)
				.orElseThrow(() -> new ResourceNotFoundException("Clientes not found for this id :: " + clienteId));
		return ResponseEntity.ok().body(client);
	}
	
	
	// this method is to add employee in the  database
	@PostMapping("/clientes")
	public Client createEmployee(@Valid @RequestBody Client client) {
		client.setId(sequenceService.generateSequence(Client.SEQUENCE_NAME));
		return clientRepo.save(client);
	}

	
	
	
	//this handler method is used to update employee information by id
	@PutMapping("/clientes/{id}")
	public ResponseEntity<Client> updateEmployee(@PathVariable(value = "id") Long clienteId,
												 @Valid @RequestBody Client clientDetails) throws ResourceNotFoundException {
		Client client = clientRepo.findById(clienteId)
				.orElseThrow(() -> new ResourceNotFoundException("clientes not found for this id :: " + clienteId));

		client.setEmailId(clientDetails.getEmailId());
		client.setLastName(clientDetails.getLastName());
		client.setFirstName(clientDetails.getFirstName());
		final Client updatedClient = clientRepo.save(client);
		return ResponseEntity.ok(updatedClient);
	}
	
	// this one is for delete an employee from the DB
	@DeleteMapping("/clientes/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long clienteId)
			throws ResourceNotFoundException {
		Client client = clientRepo.findById(clienteId)
				.orElseThrow(() -> new ResourceNotFoundException("clientes not found for this id :: " + clienteId));

		clientRepo.delete(client);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
