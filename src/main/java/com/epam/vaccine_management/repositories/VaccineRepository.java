package com.epam.vaccine_management.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epam.vaccine_management.entities.DoseData;
@Repository
public interface VaccineRepository extends JpaRepository<DoseData, Date> {
	DoseData findByDate(Date date);
	
	@Transactional
	@Query("SELECT SUM(d.covaxinAdded) FROM DoseData d")
	long noOfvacc();
}
