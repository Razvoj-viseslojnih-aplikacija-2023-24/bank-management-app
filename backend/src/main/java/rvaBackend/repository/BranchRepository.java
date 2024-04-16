package rvaBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rvaBackend.models.Bank;
import rvaBackend.models.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer> {

	List<Branch> findByAddressContainingIgnoreCase (String address);
	List<Branch> findByBank(Bank bank);
}
