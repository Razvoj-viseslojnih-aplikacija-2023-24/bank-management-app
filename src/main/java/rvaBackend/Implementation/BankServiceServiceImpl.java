package rvaBackend.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rvaBackend.models.BankService;
import rvaBackend.models.Branch;
import rvaBackend.models.User;
import rvaBackend.repository.BankServiceRepository;
import rvaBackend.servisi.BankServiceService;

@Component
public class BankServiceServiceImpl implements BankServiceService {

	@Autowired
	private BankServiceRepository repo;
	
	@Override
	public List<BankService> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existById(int id) {
		return repo.existsById(id);
	}

	@Override
	public Optional<BankService> findById(int id) {
		return repo.findById(id);
	}

	@Override
	public BankService create(BankService t) {
		return repo.save(t);
	}

	@Override
	public Optional<BankService> update(BankService t, int id) {
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
	public List<BankService> getServicesByName(String name) {
		return repo.findByNameContainingIgnoreCase(name);
	}

	@Override
	public List<BankService> getByForeignKey(Branch branch) {
		return repo.findByBranch(branch);
	}

	@Override
	public List<BankService> getByForeignKey(User user) {
		return repo.findByUser(user);
	}
}
