package rva_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rva_backend.models.Artikl;
import rva_backend.models.Porudzbina;
import rva_backend.models.StavkaPorudzbine;

public interface StavkaPorudzbineRepository extends JpaRepository<StavkaPorudzbine, Integer> {
	List<StavkaPorudzbine> findByCenaGreaterThanOrderByCena(double cena);
	List<StavkaPorudzbine> findByArtikl (Artikl artikl);
	List<StavkaPorudzbine> findByPorudzbina (Porudzbina porudzbina);
}
