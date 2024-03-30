package rvaBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rvaBackend.models.Dobavljac;
import rvaBackend.models.Porudzbina;

public interface PorudzbinaRepository extends JpaRepository<Porudzbina, Integer> {
	List<Porudzbina> findByPlacenoEquals(boolean placeno);
	List<Porudzbina> findByDobavljac (Dobavljac dobavljac);
}
