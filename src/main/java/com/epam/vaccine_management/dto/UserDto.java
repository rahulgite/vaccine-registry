package com.epam.vaccine_management.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;


@Getter
@Setter
public class UserDto {
	@Min(value = 18, message = "Age should not be less than 18")
	@Max(value = 150, message = "Age should not be greater than 150")
	int userAge;
	
	@NotBlank(message="Name is mandatory")
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	String userName;
	

	@NotNull(message = "aadhar is mandatory")
	@Range(min=100000000000L, max=999999999999L)
	long aadhar;
}
