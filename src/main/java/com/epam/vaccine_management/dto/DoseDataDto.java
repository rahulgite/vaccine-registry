package com.epam.vaccine_management.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoseDataDto {
	@NotNull
	Date date;
	@NotNull
	int covaxinAdded;
	
}
