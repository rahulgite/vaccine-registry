package com.epam.vaccine_management.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.vaccine_management.dto.DoseDataDto;
import com.epam.vaccine_management.dto.UserDto;
import com.epam.vaccine_management.entities.User;
import com.epam.vaccine_management.exceptions.ResourceNotFoundException;
import com.epam.vaccine_management.service.ProceedDossage;
import com.epam.vaccine_management.service.Vaccination;
import com.epam.vaccine_management.service.Validations;

@org.springframework.web.bind.annotation.RestController

public class RestController {

	@Autowired
	Vaccination vaccination;

	@Autowired
	Validations validations;

	@Autowired
	ProceedDossage proceedDossage;

	@GetMapping("find_dose_count")
	public ResponseEntity<String> displayCount() {

		return new ResponseEntity<>(" no of vaccines available " + vaccination.getDoseCount("covaxin"), HttpStatus.OK);
	}

	@PostMapping("update_count")
	public ResponseEntity<String> Count(@RequestParam(name = "count") int covaxinAdded,
			@RequestParam(name = "date") String date) {
		System.out.println(covaxinAdded);
		System.out.println(date);
		vaccination.setDoseCount("covaxin", covaxinAdded, date);
		return new ResponseEntity<>("successful", HttpStatus.OK);

	}

	@GetMapping("find_all_users")
	public ResponseEntity<String> displayUsers() {
		List<User> userList = vaccination.getUsersDetails();
		return new ResponseEntity<>("list of people" + userList.toString(), HttpStatus.OK);
	}

	@PostMapping("save_people")
	public ResponseEntity<String> SavePeople(@RequestParam(name = "aadhar") long aadhar,
			@RequestParam(name = "userName") String userName,
			@RequestParam(name = "userAge") int userAge,
			@RequestParam(name = "city") String city,
			@RequestParam(name = "date") String date,
			@RequestParam(name = "vaccineType") String vaccineType) {
		UserDto userDto = new UserDto();
		userDto.setAadhar(aadhar);
		userDto.setUserAge(userAge);
		userDto.setUserName(userName);
		String status = proceedDossage.giveDose(userDto, city, date, vaccineType);
		return new ResponseEntity<>(status, HttpStatus.OK);
		
	}

}
