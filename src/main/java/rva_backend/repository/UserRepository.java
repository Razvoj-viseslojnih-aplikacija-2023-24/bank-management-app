package rva_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rva_backend.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByIdNumberEquals(String idNumber);
}
