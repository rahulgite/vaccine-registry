package com.epam.vaccine_management.entities;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class VaccineStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	Date firstDoseDate;
	Date secondDoseDate;
	int vaccinationStatus;
	String vaccineType;
	
	@OneToOne(targetEntity=User.class)
	@JoinColumn(name="aadhar")
	User user;
}
