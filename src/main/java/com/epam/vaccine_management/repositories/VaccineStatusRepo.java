package com.epam.vaccine_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epam.vaccine_management.entities.VaccineStatus;
@Repository
public interface VaccineStatusRepo extends JpaRepository<VaccineStatus, Integer>{

	@Transactional
	@Query("SELECT SUM(v.vaccinationStatus) FROM VaccineStatus v")
	long noOfGivenvacc();
}
