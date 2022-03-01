package com.epam.vaccine_management.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.vaccine_management.entities.DoseData;
import com.epam.vaccine_management.entities.User;
import com.epam.vaccine_management.repositories.PersonRepository;
import com.epam.vaccine_management.repositories.VaccineRepository;
import com.epam.vaccine_management.repositories.VaccineStatusRepo;

@Service
@Transactional
public class VaccineOperations {
	@Autowired
	PersonRepository personRepo;
	@Autowired
	VaccineRepository vaccineRepo;
	@Autowired
	VaccineStatusRepo vaccineStatusRepo;
	DoseData doseData;

	public void saveUser(User user, String city, String vaccineType) {

		user.getVaccineStatus().setVaccinationStatus(1);
		user.getVaccineStatus().setVaccineType(vaccineType);
		user.getAddress().setCity(city);
		personRepo.save(user);
	}

	public String getVaccineType(long aadhar) {
		User user = personRepo.findByAadhar(aadhar);
		return user.getVaccineStatus().getVaccineType();
	}

	public List<User> getAllUsers() {
		return (List<User>) personRepo.findAll();
	}

	public User findUser(long aadhar) {
		return personRepo.findByAadhar(aadhar);
	}

	public Date getFirstDoseDate(long aadhar) {

		User user = personRepo.findByAadhar(aadhar);
		return user.getVaccineStatus().getFirstDoseDate();

	}

	public int getVaccineStatus(long aadhar) {

		User user = personRepo.findByAadhar(aadhar);
		return user.getVaccineStatus().getVaccinationStatus();

	}

	public void addSecondDoseDate(long aadhar, Date date) {
		User user = personRepo.findByAadhar(aadhar);
		user.getVaccineStatus().setSecondDoseDate(date);
		user.getVaccineStatus().setVaccinationStatus(2);
		personRepo.save(user);
	}

	public void addDoses(String vaccineType, int noOfDoses, Date date) {

		DoseData doseData = new DoseData();
		if (vaccineType.equals("covaxin")) {
			doseData.setCovaxinAdded(noOfDoses);
		}
		doseData.setDate(date);
		vaccineRepo.save(doseData);
	}

	public DoseData findDate(Date date) {
		return vaccineRepo.findByDate(date);
	}

	public void updateDose(String vaccineType, int noOfDoses, Date date) {
		DoseData doseData = findDate(date);
		if (vaccineType.equals("covaxin")) {
			doseData.setCovaxinAdded(doseData.getCovaxinAdded() + noOfDoses);
		}
		vaccineRepo.save(doseData);
	}

	public long getDoseCount(String vaccineType) {
		long doseCount = (long) 0;
		if (vaccineType.equals("covaxin")) {
			long results = vaccineRepo.noOfvacc();
			doseCount = results;
		}

		return doseCount - getDoseGiven(vaccineType);
	}

	public long getDoseGiven(String vaccineType) {
		long doseGiven = (long) 0;
		if (vaccineType.equals("covaxin")) {
			long results = vaccineStatusRepo.noOfGivenvacc();
			doseGiven = results;
		}
		return doseGiven;
	}
}
