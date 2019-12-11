package io.motionlab.repository;

import org.springframework.data.repository.CrudRepository;

import io.motionlab.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public User findByUsername(String username);
	
	public User findByEmail(String email);
}
