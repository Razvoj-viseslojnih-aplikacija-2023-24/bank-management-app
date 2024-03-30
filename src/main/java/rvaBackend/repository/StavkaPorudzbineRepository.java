package rvaBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rvaBackend.models.Artikl;
import rvaBackend.models.Porudzbina;
import rvaBackend.models.StavkaPorudzbine;

public interface StavkaPorudzbineRepository extends JpaRepository<StavkaPorudzbine, Integer> {
	List<StavkaPorudzbine> findByCenaGreaterThanOrderByCena(double cena);
	List<StavkaPorudzbine> findByArtikl (Artikl artikl);
	List<StavkaPorudzbine> findByPorudzbina (Porudzbina porudzbina);
}
