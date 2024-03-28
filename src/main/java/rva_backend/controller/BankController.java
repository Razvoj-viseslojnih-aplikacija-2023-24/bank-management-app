package rva_backend.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva_backend.Implementation.BankServiceImpl;
import rva_backend.models.Bank;

@RestController
public class BankController {

	@Autowired
	private BankServiceImpl service;
	
	@GetMapping("/bank")
	public List<Bank> getAllBanks(){
		return service.getAll();
	}
	@GetMapping("/bank/id/{id}")
	public ResponseEntity<?> getBanksById(@PathVariable int id){
		Optional<Bank> bank = service.findById(id);
		if (bank.isPresent()) {
			return ResponseEntity.ok(bank.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " does not exist.");
	}
	
	@GetMapping("/bank/tin/{tin}")
	public ResponseEntity<?> getBanksByTin(@PathVariable int tin){
		List<Bank> bank = service.getBanksByTin(tin);
		if(bank.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resources" + " with TIN: " + tin + " could not be found.");
		}
		return ResponseEntity.ok(bank);
	}
	
	@PostMapping("/bank")
	public ResponseEntity<?> createBank(@RequestBody Bank bank){
		if (service.existById(bank.getId())) {
			return ResponseEntity.status(409).body("Resource with" + " inserted values already exists.");
		}
		Bank savedBank = service.create(bank);
		URI uri = URI.create("/bank/" + savedBank.getId());
		return ResponseEntity.created(uri).body(savedBank);
	}
	
	@PutMapping("/bank/id/{id}")
	public ResponseEntity<?> updateBank(@RequestBody Bank bank, @PathVariable int id){
		Optional<Bank> updatedBank = service.update(bank, id);
		if(updatedBank.isPresent()) {
			return ResponseEntity.ok(updatedBank);
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " cannot be updated as it doesn't exist.");
	}
	
	@DeleteMapping("/bank/id/{id}")
	public ResponseEntity<?> deletedBank (@PathVariable int id){
		if(service.existById(id)) {
			service.delete(id);
			return ResponseEntity.ok("Resource with ID: " + id + " has been deleted.");
		}
		return ResponseEntity.status(404).body("Resource with requested ID cannot be deleted as it doesn't exist.");
	}
}
