package rva_backend.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rva_backend.models.Bank;
import rva_backend.repository.BankRepository;
import rva_backend.servisi.BankService;

@Component
public class BankServiceImpl implements BankService {

	@Autowired
	private BankRepository repo;
	
	@Override
	public List<Bank> getAll() {
		return repo.findAll();
		}

	@Override
	public boolean existById(int id) {
		return repo.existsById(id);
	}

	@Override
	public Optional<Bank> findById(int id) {
		return repo.findById(id);
	}

	@Override
	public Bank create(Bank t) {
		return repo.save(t);
	}

	@Override
	public Optional<Bank> update(Bank t, int id) {
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
	public List<Bank> getBanksByTin(int tin) {
		return repo.findByTinEquals(tin);
	}

}
