package com.epam.vaccine_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epam.vaccine_management.entities.User;
@Repository
public interface PersonRepository extends JpaRepository<User, Long> {
	User findByAadhar(long aadhar);
	  
}
