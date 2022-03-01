package com.epam.vaccine_management.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VaccineStatusDto {
	
	Date firstDoseDate;
	Date secondDoseDate;
	int vaccinationStatus;
	String vaccineType;
	
}
