package rvaBackend.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rvaBackend.models.BankService;
import rvaBackend.models.Branch;
import rvaBackend.models.User;
import rvaBackend.servisi.BankServiceService;
import rvaBackend.servisi.BranchService;
import rvaBackend.servisi.UserService;

@CrossOrigin
@RestController
public class BankServiceController {

	@Autowired
	private BankServiceService service;
	
	@Autowired 
	private BranchService branchService;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/bankService")
	public List<BankService> getAllBankServices(){
		return service.getAll();
	}
	
	@GetMapping("/bankService/id/{id}")
	public ResponseEntity<?> getBankServiceById(@PathVariable int id){
		Optional<BankService> bankService = service.findById(id);
		if(bankService.isPresent()) {
			return ResponseEntity.ok(bankService.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " does not exist!");
	}
	
	@GetMapping("/bankService/name/{name}")
	public ResponseEntity<?>  getServicesByName(@PathVariable String name){
		List<BankService> bankService = service.getServicesByName(name);
		if(bankService.isEmpty()) {
			return ResponseEntity.status(404).body("Resources with Cena: " + name + " do not exist!");
		}
		return ResponseEntity.ok(bankService);
	}
	
	@PostMapping("/bankService")
	public ResponseEntity<?> createBankService(@RequestBody BankService bankService){
		if(service.existById(bankService.getId())) {
			return ResponseEntity.status(409).body("Resource already exists!");
		}
		BankService savedBankService = service.create(bankService);
		URI uri = URI.create("/bankService/id/" + savedBankService.getId());
		return ResponseEntity.created(uri).body(savedBankService);
	}
	
	@PutMapping("/bankService/id/{id}")
	public ResponseEntity<?> updateBankService(@RequestBody BankService bankService, @PathVariable int id){
		Optional<BankService> updatedBankService = service.update(bankService, id);
		if(updatedBankService.isPresent()) {
			return ResponseEntity.ok(updatedBankService.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " could not be" + 
				" updated because it does not exist!");
	}
	
	@DeleteMapping("/bankService/id/{id}")
	public ResponseEntity<?> deleteBankService(@PathVariable int id ){
		if(service.existById(id)) {
			service.delete(id);
			return ResponseEntity.ok("Resource with ID: " + id + " has been deleted!");
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " could not be" + 
				" deleted because it does not exist!");
	}
	
	@GetMapping("/bankService/branch/{foreignKey}")
	public ResponseEntity<?> getServiceByBranch(@PathVariable int foreignKey){
		Optional<Branch> branch = branchService.findById(foreignKey);
		if(branch.isPresent()) {
			List<BankService> bankService = service.getByForeignKey(branch.get());
			if(branch.isEmpty()) {
				return ResponseEntity.status(404).body("Resources with foreign key: " + foreignKey
						+ " do not exist!");
			}else {
				return ResponseEntity.ok(bankService);
			}
		}
		return ResponseEntity.status(400).body("Invalid foreign key!");
	}
	
	@GetMapping("/bankService/user/{foreignKey}")
	public ResponseEntity<?> getServiceByUser(@PathVariable int foreignKey){
		Optional<User> user = userService.findById(foreignKey);
		if(user.isPresent()) {
			List<BankService> bankService = service.getByForeignKey(user.get());
			if(bankService.isEmpty()) {
				return ResponseEntity.status(404).body("Resources with foreign key: " + foreignKey
						+ " do not exist!");
			}else {
				return ResponseEntity.ok(bankService);
			}
		}
		return ResponseEntity.status(400).body("Invalid foreign key!");
	}
}
