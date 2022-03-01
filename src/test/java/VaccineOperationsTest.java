
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.epam.vaccine_management.VaccineManagementSystemApplication;
import com.epam.vaccine_management.entities.Address;
import com.epam.vaccine_management.entities.DoseData;
import com.epam.vaccine_management.entities.User;
import com.epam.vaccine_management.entities.VaccineStatus;
import com.epam.vaccine_management.repositories.PersonRepository;
import com.epam.vaccine_management.repositories.VaccineRepository;
import com.epam.vaccine_management.repositories.VaccineStatusRepo;
import com.epam.vaccine_management.service.VaccineOperations;

@SpringBootTest(classes= {VaccineManagementSystemApplication.class})
@ExtendWith(MockitoExtension.class)
public class VaccineOperationsTest {

	@InjectMocks
	VaccineOperations db;
	@Mock
	PersonRepository personRepo;
	@Mock
	VaccineRepository vaccineRepo;
	@Mock
	VaccineStatusRepo vaccineStatusRepo;
	@Mock
	DoseData doseData;

	@Test
	void testSaveUser() {
		User user = new User();
		Long aadhar = 123412341234L;
		Date d = new Date(20 - 04 - 2021);
		user.setAadhar(aadhar);
		user.setUserName("sri");
		user.setVaccineStatus(new VaccineStatus());
		user.getVaccineStatus().setFirstDoseDate(d);
		String city = "kkd";
		String vaccineType = "covaxin";
		user.setAddress(new Address());
		db.saveUser(user,city,vaccineType);
		verify(personRepo).save(user);
	}

	@Test
	void addSecondDoseDateTest() {
		User user = new User();
		Long aadhar = 123412341234L;
		Date date = new Date(20 - 04 - 2021);
		user.setAadhar(aadhar);
		user.setUserName("sri");
		user.setVaccineStatus(new VaccineStatus());
		user.getVaccineStatus().setFirstDoseDate(date);

		user.getVaccineStatus().setSecondDoseDate(date);
		user.getVaccineStatus().setVaccinationStatus(2);
		when(personRepo.findByAadhar(aadhar)).thenReturn(user);
		db.addSecondDoseDate( aadhar,  date);
		verify(personRepo, times(1)).save(user);
	}
	@Test
	void addDosesTest(){
		String vaccineType = "covaxin";
		Date date = new Date(20 - 04 - 2021);	
		db.addDoses( vaccineType, 77,  date);
//		verify(vaccineRepo).save(doseData);
	}

	
	@Test
	void testFindUser() {

		User user = new User();
		Long aadhar = 123412341234L;
		Date d = new Date(20 - 04 - 2021);
		user.setAadhar(aadhar);
		user.setUserName("sri");
		user.setVaccineStatus(new VaccineStatus());
		user.getVaccineStatus().setFirstDoseDate(d);
		when(personRepo.findByAadhar(aadhar)).thenReturn(user);
		
		assertEquals(user, db.findUser(aadhar));
	}

	@Test
	void testUserVaccineStatus() {

		User user = new User();
		Long aadhar = 123412341234L;
		Date d = new Date(20 - 04 - 2021);
		user.setAadhar(aadhar);
		user.setUserName("sri");
		user.setVaccineStatus(new VaccineStatus());
		user.getVaccineStatus().setFirstDoseDate(d);
		user.setAddress(new Address());
		when(personRepo.findByAadhar(aadhar)).thenReturn(user);
		
		assertEquals(0, db.getVaccineStatus(aadhar));
	}
	@Test
	void getDoseCountTest() {
		
		when(vaccineRepo.noOfvacc()).thenReturn(77L);
		when(vaccineStatusRepo.noOfGivenvacc()).thenReturn(0L);
		assertEquals(77,db.getDoseCount("covaxin"));
	}
	@Test
	void updateDoseTest() {
		DoseData dd = new DoseData();
		Date date = new Date(20 - 04 - 2021);
		when(vaccineRepo.findByDate(date)).thenReturn(dd);
		db.updateDose("covaxin", 77, date);
//		verify(vaccineRepo,times(1)).save(doseData);
	}
	@Test
	void testGetFirstDoseDate() {
		User user = new User();
		Long aadhar = 123412341234L;
		Date d = new Date(20 - 04 - 2021);
		user.setAadhar(aadhar);
		user.setUserName("sri");
		user.setVaccineStatus(new VaccineStatus());
		user.getVaccineStatus().setFirstDoseDate(d);
		when(personRepo.findByAadhar(aadhar)).thenReturn(user);
		assertEquals(new Date(20 - 04 - 2021), db.getFirstDoseDate(aadhar));
	}


	@Test
	void testGetVaccineType() {
		User user = new User();
		Long aadhar = 123412341234L;
		Date d = new Date(20 - 04 - 2021);
		user.setAadhar(aadhar);
		user.setUserName("sri");
		user.setVaccineStatus(new VaccineStatus());
		user.getVaccineStatus().setFirstDoseDate(d);
		user.getVaccineStatus().setVaccineType("covaxin");
		when(personRepo.findByAadhar(aadhar)).thenReturn(user);
		
		assertEquals("covaxin", db.getVaccineType(aadhar));
	}

	@Test
	void testFindDate() {
		DoseData dd = new DoseData();
		Date date = new Date(20 - 04 - 2021);
		when(vaccineRepo.findByDate(date)).thenReturn(dd);
		assertEquals(dd, db.findDate(date));
	}

}
