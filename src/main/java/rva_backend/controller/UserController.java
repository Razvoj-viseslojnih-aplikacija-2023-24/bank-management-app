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

import rva_backend.models.User;
import rva_backend.servisi.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/user")
	public List<User> getAllUsers(){
		return service.getAll();
	}
	@GetMapping("/user/id/{id}")
	public ResponseEntity<?> getUsersById(@PathVariable int id){
		Optional<User> user = service.findById(id);
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " does not exist.");
	}
	
	@GetMapping("/user//{idNumber}")
	public ResponseEntity<?> getUsersbyIdNumber(@PathVariable String idNumber){
		List<User> user = service.getUsersByIdNumber(idNumber);
				if(user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resources" + " with identification number: " + idNumber + " could not be found.");
		}
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/user")
	public ResponseEntity<?> createUser(@RequestBody User user){
		if (service.existById(user.getId())) {
			return ResponseEntity.status(409).body("Resource with" + " inserted values already exists.");
		}
		User savedUser = service.create(user);
		URI uri = URI.create("/user/" + savedUser.getId());
		return ResponseEntity.created(uri).body(savedUser);
	}
	
	@PutMapping("/user/id/{id}")
	public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable int id){
		Optional<User> updatedUser = service.update(user, id);
		if(updatedUser.isPresent()) {
			return ResponseEntity.ok(updatedUser);
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " cannot be updated as it doesn't exist.");
	}
	
	@DeleteMapping("/user/id/{id}")
	public ResponseEntity<?> deletedUser (@PathVariable int id){
		if(service.existById(id)) {
			service.delete(id);
			return ResponseEntity.ok("Resource with ID: " + id + " has been deleted.");
		}
		return ResponseEntity.status(404).body("Resource with requested ID cannot be deleted as it doesn't exist.");
	}
}
