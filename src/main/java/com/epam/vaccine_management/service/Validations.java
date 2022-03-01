package com.epam.vaccine_management.service;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service("validations")
public class Validations {
	static final String DATE_FORMAT = "dd-MM-yyyy";

	public boolean isAadharValid(Long aadhar) {
		boolean validity = false;
		String userAadhar = "" + aadhar;
		String regex = "[0-9]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(userAadhar);
		if (userAadhar.length() == 12 && matcher.matches()) {
			validity = true;
		}
		return validity;

	}

	public boolean isAgeValid(int userAge) {
		boolean validity = false;
		if (userAge >= 18 && userAge <= 100) {
			validity = true;
		}
		return validity;
	}

	public boolean isNameValid(String userName) {
		return ((!userName.equals("")) && (userName != null) && (userName.matches("^[a-zA-Z]*$")));
	}



	public boolean isDurationValid(Date firstDoseDate, Date secondDoseDate) {
		boolean result = false;
		long differ = secondDoseDate.getTime() - firstDoseDate.getTime();
		long diffInMillies = Math.abs(differ);
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    if(diff>=30 && differ >0) {
	    	result=true;
	    }
		return result;
	}
}