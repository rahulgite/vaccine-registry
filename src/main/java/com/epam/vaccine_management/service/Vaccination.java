package com.epam.vaccine_management.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.vaccine_management.dto.UserDto;
import com.epam.vaccine_management.entities.Address;
import com.epam.vaccine_management.entities.User;
import com.epam.vaccine_management.entities.VaccineStatus;

@Service
public class Vaccination {
	@Autowired
	VaccineOperations vaccineOperations;
	ModelMapper m = new ModelMapper();
	
	public Vaccination() {
	}
	public Vaccination(VaccineOperations vaccineOperations) {
		this.vaccineOperations = vaccineOperations;
	}

	public void giveFirstDose(UserDto userDto, String date, String city, String vaccineType) {
		User user = m.map(userDto, User.class);
		Date sdate = null;
		try {
			sdate = new SimpleDateFormat("dd-MM-yyyy").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user.setVaccineStatus(new VaccineStatus());
		user.getVaccineStatus().setFirstDoseDate(sdate);
		user.getVaccineStatus().setVaccinationStatus(1);
		user.getVaccineStatus().setVaccineType(vaccineType);
		user.setAddress(new Address());
		user.getAddress().setCity(city);
		vaccineOperations.saveUser(user, city, vaccineType);
	}

	public void giveSecondDose(long aadhar, String date) {
		Date sdate = null;
		try {
			sdate = new SimpleDateFormat("dd-MM-yyyy").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		vaccineOperations.addSecondDoseDate(aadhar, sdate);
		
	}

	public Date getFirstDoseDate(long aadhar) {
		return vaccineOperations.getFirstDoseDate(aadhar);
	}

	public List<User> getUsersDetails() {
		return vaccineOperations.getAllUsers();
	}

	public boolean isUserExists(long aadhar) {
		boolean result = false;
		if (vaccineOperations.findUser(aadhar) != null) {
			result = true;
		}
		return result;
	}

	public int getVaccineStatus(long aadhar) {
		return vaccineOperations.getVaccineStatus(aadhar);
	}

	public void setDoseCount(String vaccineType, int noOfDoses, String date) {
		Date sdate = null;
		try {
			sdate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (vaccineOperations.findDate(sdate) == null) {
			vaccineOperations.addDoses(vaccineType, noOfDoses, sdate);
		} else {
			vaccineOperations.updateDose(vaccineType, noOfDoses, sdate);
		}
	}

	public String getVaccineType(long aadhar) {
		return vaccineOperations.getVaccineType(aadhar);
	}

	public long getDoseCount(String vaccineType) {
		return vaccineOperations.getDoseCount(vaccineType);
	}

}
