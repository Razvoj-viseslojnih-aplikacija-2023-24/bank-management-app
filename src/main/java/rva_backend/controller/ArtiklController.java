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

import rva_backend.Implementation.ArtiklServiceImpl;
import rva_backend.models.Artikl;

@RestController
public class ArtiklController {
	
	@Autowired
	private ArtiklServiceImpl service;
	
	@GetMapping("/artikl")
	public List<Artikl> getAllArtikls(){
		return service.getAll();
	}
	
	@GetMapping("/artikl/id/{id}")
	public ResponseEntity<?> getArtiklById(@PathVariable int id){
		Optional<Artikl> artikl = service.findById(id);
		if (artikl.isPresent()) {
			return ResponseEntity.ok(artikl.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " does not exist.");
	}
	
	@GetMapping("/artikl/naziv/{naziv}")
	public ResponseEntity<?> getArtiklsByNaziv(@PathVariable String naziv){
		List<Artikl> artikls = service.getArtiklsByNaziv(naziv);
		if(artikls.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resources" + " with Naziv: " + naziv + " could not be found.");
		}
		return ResponseEntity.ok(artikls);
	}
	
	@PostMapping("/artikl")
	public ResponseEntity<?> createArtikl(@RequestBody Artikl artikl){
		if (service.existById(artikl.getId())) {
			return ResponseEntity.status(409).body("Resource with" + " inserted values already exists.");
		}
		Artikl savedArtikl = service.create(artikl);
		URI uri = URI.create("/artikl/" + savedArtikl.getId());
		return ResponseEntity.created(uri).body(savedArtikl);
	}
	
	@PutMapping("/artikl/id/{id}")
	public ResponseEntity<?> updateArtikl(@RequestBody Artikl artikl, @PathVariable int id){
		Optional<Artikl> updatedArtikl = service.update(artikl, id);
		if(updatedArtikl.isPresent()) {
			return ResponseEntity.ok(updatedArtikl);
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " cannot be updated as it doesn't exist.");
	}
	
	@DeleteMapping("/artikl/id/{id}")
	public ResponseEntity<?> deletedArtikl (@PathVariable int id){
		if(service.existById(id)) {
			service.delete(id);
			return ResponseEntity.ok("Respurce with ID: " + id + " has been deleted.");
		}
		return ResponseEntity.status(404).body("Resource with requested ID cannot be deleted as it doesn't exist.");
	}
}