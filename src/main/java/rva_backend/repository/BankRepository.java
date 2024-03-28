package rva_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rva_backend.models.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer> {

	List<Bank> findByTinEquals(int tin);
}
