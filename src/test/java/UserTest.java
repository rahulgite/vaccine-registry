

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.vaccine_management.entities.Address;
import com.epam.vaccine_management.entities.User;
import com.epam.vaccine_management.entities.VaccineStatus;

class UserTest {
//	@Autowired
	User user = new User();

	@Test
	void getUserAgeTest() {
		user.setAadhar(123412348074L);
		user.setUserName("sriman");
		user.setVaccineStatus(new VaccineStatus());
		user.getVaccineStatus().setFirstDoseDate(new Date(20-06-2021));

		user.setUserAge(22);
		assertEquals(22,user.getUserAge());
	}
	@Test
	void getUserAddress() {
		Address address = new Address();
		user.setAddress(address);
		assertEquals(address,user.getAddress());
	}
	@Test
	void getUserVaccineStatus() {
		VaccineStatus vaccineStatus = new VaccineStatus();
		user.setAadhar(123412348074L);
		user.setUserAge(20);
		user.setUserName("sriman");
		user.setVaccineStatus(vaccineStatus);
		assertEquals(vaccineStatus,user.getVaccineStatus());
	}
	@Test
	void setUserNameTest() {
		user.setAadhar(123412348074L);
		user.setUserAge(20);
		user.setVaccineStatus(new VaccineStatus());
		user.getVaccineStatus().setFirstDoseDate(new Date(20-06-2021));

		user.setUserName("hari");
		assertEquals("hari",user.getUserName());
	}
	@Test
	void setAadharTest() {
		user.setUserAge(20);
		user.setUserName("sriman");
		user.setVaccineStatus(new VaccineStatus());
		user.getVaccineStatus().setFirstDoseDate(new Date(20-06-2021));

		user.setAadhar(123412341234L);
		assertEquals(123412341234L,user.getAadhar(),0.0001);
	}
	
	@Test
	void printUserTest() {
		user.setAadhar(123412348074L);
		user.setUserAge(20);
		user.setUserName("sriman");
		
		assertEquals("User [aadhar=123412348074, userAge=20, userName=sriman]",user.toString());
	}
}
