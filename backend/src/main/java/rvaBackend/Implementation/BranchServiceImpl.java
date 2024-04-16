package rvaBackend.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rvaBackend.models.Bank;
import rvaBackend.models.Branch;
import rvaBackend.repository.BranchRepository;
import rvaBackend.servisi.BranchService;

@Component
public class BranchServiceImpl implements BranchService {

	@Autowired
	private BranchRepository repo;
	
	@Override
	public List<Branch> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existById(int id) {
		return repo.existsById(id);
	}

	@Override
	public Optional<Branch> findById(int id) {
		return repo.findById(id);
	}

	@Override
	public Branch create(Branch t) {
		return repo.save(t);
	}

	@Override
	public Optional<Branch> update(Branch t, int id) {
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
	public List<Branch> getBranchesByAddress(String address) {
		return repo.findByAddressContainingIgnoreCase(address);
	}

	@Override
	public List<Branch> getForeignKey(Bank bank) {
		return repo.findByBank(bank);
	}

}
