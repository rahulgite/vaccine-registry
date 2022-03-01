package com.epam.vaccine_management.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DoseData {
	@Id
	Date date;
	int covaxinAdded;
}
