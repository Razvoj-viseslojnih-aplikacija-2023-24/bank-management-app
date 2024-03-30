package rvaBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rvaBackend.models.Artikl;

public interface ArtiklRepository extends JpaRepository<Artikl, Integer> {
	List<Artikl> findByNazivContainingIgnoreCase(String naziv);
}
