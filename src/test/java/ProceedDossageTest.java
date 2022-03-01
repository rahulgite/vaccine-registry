import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.vaccine_management.dto.UserDto;
import com.epam.vaccine_management.entities.Address;
import com.epam.vaccine_management.entities.User;
import com.epam.vaccine_management.entities.VaccineStatus;
import com.epam.vaccine_management.service.ProceedDossage;
import com.epam.vaccine_management.service.Vaccination;
import com.epam.vaccine_management.service.Validations;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;

@ExtendWith(MockitoExtension.class)
class ProceedDossageTest {

	@InjectMocks
	ProceedDossage p;
	@Mock
	Vaccination vaccination;// = new Vaccination();
	
	@Mock
	Validations validations;
	

	@Test
	void secondDossagetest() {
		String city="bvrm";
		String vaccineType="covaxin";
		long aadhar = 123412348074L;
		String date = "20-04-2021";
		Date d1 =new Date(20-03-2021);
		Date d2 = new Date(20-04-2021);
		when(vaccination.getFirstDoseDate(aadhar)).thenReturn(d1);
		when(validations.isDurationValid(any(Date.class),any(Date.class))).thenReturn(true);
		doNothing().when(vaccination).giveSecondDose(aadhar,"20-04-2021");
		assertEquals("Second Dose completed",p.secondDossage( aadhar, date, vaccineType));
	}



	@Test
	void secondDossagetimetest() {
		String city="bvrm";
		String vaccineType="covaxin";
		long aadhar = 123412348074L;
		String date = "20-04-2021";
		Date d1 =new Date(20-03-2021);
		Date d2 = new Date(20-04-2021);
		when(vaccination.getFirstDoseDate(aadhar)).thenReturn(d1);
		when(validations.isDurationValid(any(Date.class),any(Date.class))).thenReturn(false);
		assertEquals("Still time for second dose is left",p.secondDossage( aadhar, date, vaccineType));
	}
	
	@Test
	void firstDossagetest() {
		String city="bvrm";
		String vaccineType="covaxin";
		
		User user = new User();
		user.setAadhar(123412348074L);
		user.setUserAge(19);
		user.setUserName("sriman");
		user.setVaccineStatus(new VaccineStatus());
		user.getVaccineStatus().setFirstDoseDate(new Date(20-04-2021));
		user.setAddress(new Address());
		user.getAddress().setCity(city);
		user.getVaccineStatus().setVaccinationStatus(1);
		user.getVaccineStatus().setVaccineType(vaccineType);
		
		UserDto userDto = new UserDto();
		userDto.setAadhar(123412348074L);
		userDto.setUserAge(19);
		userDto.setUserName("sriman");
		
		String date = "20-04-2021";
		doNothing().when(vaccination).giveFirstDose(userDto, date , city, vaccineType);
		assertEquals("First dose completed",p.firstDossage( userDto, date, city,vaccineType));

	}

}
