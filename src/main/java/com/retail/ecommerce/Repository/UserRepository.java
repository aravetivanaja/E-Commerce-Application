package com.retail.ecommerce.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.ecommerce.Model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	boolean existsByEmail(String email);
	Optional<User> findByUserName(String username);

}
