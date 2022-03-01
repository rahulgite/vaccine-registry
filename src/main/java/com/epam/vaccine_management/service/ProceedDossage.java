package com.epam.vaccine_management.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.vaccine_management.dto.UserDto;
import com.epam.vaccine_management.exceptions.ResourceNotFoundException;

@Service
public class ProceedDossage {
	@Autowired
	Vaccination vaccination;
	@Autowired
	Validations validations;

	private String firstDose = "First dose completed";
	private String secondDose = "Second Dose completed";
	private String timeLeft = "Still time for second dose is left";
	private String vaccineCompleted = "Vaccination already completed";

	public String giveDose(UserDto userDto, String city, String date, String vaccineType) {
		String status;
		long aadhar = userDto.getAadhar();
		if (vaccination.getDoseCount("covaxin") > 0) {
			if (!vaccination.isUserExists(aadhar)) {
				status = firstDossage(userDto, date, city, vaccineType);
			} else if (vaccination.getVaccineStatus(aadhar) == 2) {
				status = vaccineCompleted;
			} else {
				String vaccineType1 = vaccination.getVaccineType(aadhar);
				status = secondDossage(aadhar, date, vaccineType1);
			}
		} else {
			throw new ResourceNotFoundException("no doses left");
		}
		return status;
	}

	public String firstDossage(UserDto userDto, String date, String city, String vaccineType) {
		vaccination.giveFirstDose(userDto, date, city, vaccineType);
		return firstDose;
	}

	public String secondDossage(long aadhar, String date, String vaccineType) {

		Date firstDoseDate = vaccination.getFirstDoseDate(aadhar);
		Date secondDoseDate = null;
		try {
			secondDoseDate = new SimpleDateFormat("dd-MM-yyyy").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (validations.isDurationValid(firstDoseDate, secondDoseDate)) {
			vaccination.giveSecondDose(aadhar, date);
			return secondDose;
		} else {
			return timeLeft;
		}
	}

}
