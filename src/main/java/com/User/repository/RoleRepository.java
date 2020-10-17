package com.User.repository;

import org.springframework.stereotype.Repository;

import com.User.model.ERole;
import com.User.model.Role;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;



@Repository
public interface RoleRepository extends MongoRepository<Role, String>{
	Optional<Role> findByName(ERole name);

}
