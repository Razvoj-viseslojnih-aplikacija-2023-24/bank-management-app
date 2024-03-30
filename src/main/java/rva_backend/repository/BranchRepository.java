package rva_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rva_backend.models.Bank;
import rva_backend.models.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer> {

	List<Branch> findByAddressContainingIgnoreCase (String address);
	List<Branch> findByBank(Bank bank);
}
