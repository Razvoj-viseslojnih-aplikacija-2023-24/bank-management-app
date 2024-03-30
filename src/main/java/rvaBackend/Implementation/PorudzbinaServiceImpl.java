package rvaBackend.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rvaBackend.models.Dobavljac;
import rvaBackend.models.Porudzbina;
import rvaBackend.repository.PorudzbinaRepository;
import rvaBackend.servisi.PorudzbinaService;

@Component
public class PorudzbinaServiceImpl implements PorudzbinaService {

	@Autowired
	private PorudzbinaRepository repo;
	
	@Override
	public List<Porudzbina> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existById(int id) {
		return repo.existsById(id);
	}

	@Override
	public Optional<Porudzbina> findById(int id) {
		return repo.findById(id);
	}

	@Override
	public Porudzbina create(Porudzbina t) {
		return repo.save(t);
	}

	@Override
	public Optional<Porudzbina> update(Porudzbina t, int id) {
		if(existById(id)) {
			t.setId(id);
			return Optional.of(repo.save(t));
		}
		return Optional.empty();
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
		
	}

	@Override
	public List<Porudzbina> getPorudzbinaByPlaceno(boolean placeno) {
		return repo.findByPlacenoEquals(placeno);
	}

	@Override
	public List<Porudzbina> getForeignKey(Dobavljac dobavljac) {
		return repo.findByDobavljac(dobavljac);
	}
	
}
