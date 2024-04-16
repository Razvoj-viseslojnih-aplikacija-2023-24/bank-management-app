package rvaBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rvaBackend.models.BankService;
import rvaBackend.models.Branch;
import rvaBackend.models.User;

public interface BankServiceRepository extends JpaRepository<BankService, Integer> {

	List<BankService> findByNameContainingIgnoreCase(String name);
	List<BankService> findByBranch (Branch branch);
	List<BankService> findByUser (User user);
}
