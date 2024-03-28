package rva_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rva_backend.models.Dobavljac;

public interface DobavljacRepository extends JpaRepository<Dobavljac, Integer> {
	List<Dobavljac> findByAdresaContainingIgnoreCase(String adresa);
}
