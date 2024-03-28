package rva_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rva_backend.models.BankService;
import rva_backend.models.Branch;
import rva_backend.models.User;

public interface BankServiceRepository extends JpaRepository<BankService, Integer> {

	List<BankService> findByNameContainingIgnoreCase(String name);
	List<BankService> findByBranch (Branch branch);
	List<BankService> findByUser (User user);
}
