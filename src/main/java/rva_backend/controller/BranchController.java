package rva_backend.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva_backend.models.Bank;
import rva_backend.models.Branch;
import rva_backend.servisi.BankService;
import rva_backend.servisi.BranchService;

@RestController
public class BranchController {

	@Autowired
	private BranchService service;
	
	@Autowired
	private BankService bankService;
	
	@GetMapping("/branch")
	public List<Branch> getAllBranches(){
		return service.getAll();
	}
	
	@GetMapping("/branch/id/{id}")
	public ResponseEntity<?> getBranchById(@PathVariable int id){
		Optional<Branch> branch = service.findById(id);
		if(branch.isPresent()) {
			return ResponseEntity.ok(branch.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " does not exist!");
	}
	
	@GetMapping("/branch/address/{address}")
	public ResponseEntity<?> getBranchesByAddress(@PathVariable String address){
		List<Branch> branch = service.getBranchesByAdress(address);
		if(branch.isEmpty()) {
			return ResponseEntity.status(404).body("Resources with Address: " + address + " do not exist!");
		}
		return ResponseEntity.ok(branch);
	}
	
	@PostMapping("/branch")
	public ResponseEntity<?> createBranch(@RequestBody Branch branch){
		if(service.existById(branch.getId())) {
			return ResponseEntity.status(409).body("Resource already exists!");
		}
		Branch savedBranch = service.create(branch);
		URI uri = URI.create("/branch/id/" + savedBranch.getId());
		return ResponseEntity.created(uri).body(savedBranch);
	}
	
	@PutMapping("/branch/id/{id}")
	public ResponseEntity<?> updateBranch(@RequestBody Branch branch, @PathVariable int id){
		Optional<Branch> updatedBranch = service.update(branch, id);
		if(updatedBranch.isPresent()) {
			return ResponseEntity.ok(updatedBranch.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " could not be" + 
				" updated because it does not exist!");
	}
	
	@DeleteMapping("/branch/id/{id}")
	public ResponseEntity<?> deleteBranch(@PathVariable int id ){
		if(service.existById(id)) {
			service.delete(id);
			return ResponseEntity.ok("Resource with ID: " + id + " has been deleted!");
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " could not be" + 
				" deleted because it does not exist!");
	}
	
	@GetMapping("/branch/bank/{foreignKey}")
	public ResponseEntity<?> getBranchByBank(@PathVariable int foreignKey){
		Optional<Bank> bank = bankService.findById(foreignKey);
		if(bank.isPresent()) {
			List<Branch> branch = service.getForeignKey(bank.get());
			if(branch.isEmpty()) {
				return ResponseEntity.status(404).body("Resources with foreign key: " + foreignKey
						+ " do not exist!");
			}else {
				return ResponseEntity.ok(bank);
			}
		}
		return ResponseEntity.status(400).body("Invalid foreign key!");
	}
}
