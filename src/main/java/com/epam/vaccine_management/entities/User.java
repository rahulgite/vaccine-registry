package com.epam.vaccine_management.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

	@Id
	long aadhar;
	int userAge;
	String userName;
	@OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	VaccineStatus vaccineStatus;
	@OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	Address address;

	public void setVaccineStatus(VaccineStatus vaccineStatus) {
		vaccineStatus.setUser(this);
		this.vaccineStatus = vaccineStatus;
	}

	public void setAddress(Address address) {
		address.setUser(this);
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [aadhar=" + aadhar + ", userAge=" + userAge + ", userName=" + userName + "]";
	}

}