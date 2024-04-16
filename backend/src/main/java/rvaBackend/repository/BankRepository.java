package rvaBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rvaBackend.models.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer> {

	List<Bank> findByTinEquals(int tin);
}
