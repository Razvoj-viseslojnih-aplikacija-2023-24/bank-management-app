package rvaBackend.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rvaBackend.models.User;
import rvaBackend.repository.UserRepository;
import rvaBackend.servisi.UserService;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public List<User> getAll() {
		return repo.findAll();
		}

	@Override
	public boolean existById(int id) {
		return repo.existsById(id);
	}

	@Override
	public Optional<User> findById(int id) {
		return repo.findById(id);
	}

	@Override
	public User create(User t) {
		return repo.save(t);
	}

	@Override
	public Optional<User> update(User t, int id) {
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
	public List<User> getUsersByIdNumber(String idNumber) {
		return repo.findByIdNumberEquals(idNumber);
	}

}
