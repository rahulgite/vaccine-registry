package com.epam.vaccine_management.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.epam.vaccine_management.dto.UserDto;
import com.epam.vaccine_management.exceptions.ResourceNotFoundException;
import com.epam.vaccine_management.service.ProceedDossage;
import com.epam.vaccine_management.service.Vaccination;
import com.epam.vaccine_management.service.Validations;

@org.springframework.stereotype.Controller
public class Controller {

	@Autowired
	Vaccination vaccination;
	@Autowired
	Validations validations;
	@Autowired
	ProceedDossage proceedDossage;
	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("vaccine_management")
	public String vaccine_manager() {
		return "vaccine_management";
	}

	@GetMapping("dosage_management")
	public String dosage_manager() {
		return "dosage_management";
	}

	@GetMapping("vaccine")
	public String savePath() {
		return "vaccination";
	}

	@GetMapping("add_doses")
	public String addDosages() {
		return "addDoses";
	}

	@GetMapping("find_dosecount")
	public ModelAndView displayCount() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		String count = restTemplate
				.exchange("http://localhost:8080/find_dose_count", HttpMethod.GET, entity, String.class).getBody();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("success");
		mv.addObject("message", "no of vaccines available " + count);
		return mv;
	}

	@PostMapping("updatecount")
	public ModelAndView Count(int covaxinAdded, String date) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("count", covaxinAdded);
		map.add("date", date);
		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);
		restTemplate.postForObject("http://localhost:8080/update_count", request, String.class);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("success");
		mv.addObject("message", "added");
		return mv;

	}

	@GetMapping("findallusers")
	public ModelAndView displayUsers() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		String users = restTemplate
				.exchange("http://localhost:8080/find_all_users", HttpMethod.GET, entity, String.class).getBody();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("success");
		mv.addObject("message", users);
		return mv;
	}

	@PostMapping("savepeople")
	public ModelAndView savePeople(UserDto userDto, String city, String date, String vaccineType)
			throws ResourceNotFoundException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

		map.add("aadhar", userDto.getAadhar());
		map.add("userAge", userDto.getUserAge());
		map.add("userName", userDto.getUserName());
		map.add("city", city);
		map.add("date", date);
		map.add("vaccineType", vaccineType);
		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);
		ResponseEntity<String> res = restTemplate.exchange("http://localhost:8080/save_people", HttpMethod.POST, request, String.class);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("success");
		mv.addObject("message", res.getBody());
		return mv;
	}

}
