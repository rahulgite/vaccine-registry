
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.epam.vaccine_management.dto.AddressDto;
import com.epam.vaccine_management.dto.DoseDataDto;
import com.epam.vaccine_management.dto.UserDto;
import com.epam.vaccine_management.dto.VaccineStatusDto;
import com.epam.vaccine_management.entities.Address;
import com.epam.vaccine_management.entities.DoseData;
import com.epam.vaccine_management.entities.User;
import com.epam.vaccine_management.entities.VaccineStatus;
import com.epam.vaccine_management.service.VaccineOperations;
import com.epam.vaccine_management.service.Vaccination;


@ExtendWith(MockitoExtension.class)
class VaccinationTest {
	
    @InjectMocks
	Vaccination vaccination; 
    @Mock
    VaccineOperations vaccineOperations;
    @Mock
    ModelMapper modelMapper;
    
    @Test
	void testUserExistance() {
		User user = new User();
		user.setAadhar(123412341234L);
		user.setUserAge(19);
		user.setUserName("sri");
		user.setVaccineStatus(new VaccineStatus());
		user.getVaccineStatus().setFirstDoseDate(new Date(20-04-2021));

		when(vaccineOperations.findUser(123412341234L)).thenReturn(user);
		assertTrue(vaccination.isUserExists((long) 123412341234L));
		
	}
	@Test
	void testNoUserExistance() {
		assertEquals(false,vaccination.isUserExists((long) 123412348074L));
		
	}
	
	@Test
	void testgetFirstDoseDate() {
		when(vaccineOperations.getFirstDoseDate(123412348074L)).thenReturn(new Date(20-04-2020));
		assertEquals(new Date(20-04-2020),vaccination.getFirstDoseDate((long) 123412348074L));
		
	}
	
	@Test
	void testAllUsers() {
		User user1 = new User();
		User user2 = new User();
		user1.setAadhar(123412341234L);
		user1.setUserAge(19);
		user1.setUserName("sri");
		user1.setVaccineStatus(new VaccineStatus());
		user1.getVaccineStatus().setFirstDoseDate(new Date(20-04-2021));

		user2.setAadhar(123412348074L);
		user2.setUserAge(20);
		user2.setUserName("sriman");
		user2.setVaccineStatus(new VaccineStatus());
		user2.getVaccineStatus().setFirstDoseDate(new Date(20-06-2021));

		
		List<User> list = new ArrayList<>();
		list.add(user1);
		list.add(user2);
		when(vaccineOperations.getAllUsers()).thenReturn(list);
		assertEquals(list,vaccination.getUsersDetails());
		
	}
	@Test
	void testGetVaccineStatus() {
		Long aadhar = 123412348074L;
		when(vaccineOperations.getVaccineStatus(aadhar)).thenReturn(1);
		assertEquals(1,vaccination.getVaccineStatus(aadhar));
	}
	@Test
	void testGetVaccineType() {
		Long aadhar = 123412348074L;
		when(vaccineOperations.getVaccineType(aadhar)).thenReturn("covaxin");
		assertEquals("covaxin",vaccination.getVaccineType(aadhar));
	}
	@Test
	void testGetDoseCount() {
		String vaccineType="covaxin";
		when(vaccineOperations.getDoseCount(vaccineType)).thenReturn(77L);
		assertEquals(77L,vaccination.getDoseCount(vaccineType),0.0001);
	}
	
	@Test
	void testSetDoseCount() {
		String vaccineType="covaxin";
		Date date = new Date(20-04-2021);
		int noOfDoses = 77;
		when(vaccineOperations.findDate(date)).thenReturn(null);
		doNothing().when(vaccineOperations).addDoses(vaccineType, noOfDoses , date);
		vaccination.setDoseCount(vaccineType, noOfDoses,"22-04-2021");
		verify(vaccineOperations, times(1)).addDoses(vaccineType, noOfDoses, date);
	}

	@Test
	void testSetDoseCountBranch() {
		String vaccineType="covaxin";
		Date date = new Date(22-04-2021);
		int noOfDoses = 77;
		DoseData d = new DoseData();
		d.setCovaxinAdded(100);
		d.setDate(date);
		DoseDataDto ddd = new DoseDataDto();
		ddd.setCovaxinAdded(noOfDoses);
		ddd.setDate(date);
		when(vaccineOperations.findDate(any(Date.class))).thenReturn(d);
		doNothing().when(vaccineOperations).updateDose(eq(vaccineType), eq(noOfDoses), any(Date.class));
		vaccination.setDoseCount(vaccineType, noOfDoses,"22-04-2021" );
		verify(vaccineOperations, times(1)).updateDose(vaccineType, noOfDoses, date);
	}

	@Test
	void testGiveFirstDose() {
		String city="bvrm";
		String vaccineType="covaxin";
		
		User user = new User();
		user.setAadhar(123412348074L);
		user.setUserAge(19);
		user.setUserName("sriman");
		user.setVaccineStatus(new VaccineStatus());
		user.getVaccineStatus().setFirstDoseDate(new Date(20-04-2021));
		Address a = new Address();
		a.setCity(city);
		a.setUser(user);
		user.setAddress(a);
		user.getVaccineStatus().setVaccinationStatus(1);
		user.getVaccineStatus().setVaccineType(vaccineType);
		
		UserDto userDto = new UserDto();
		userDto.setAadhar(123412348074L);
		userDto.setUserAge(19);
		userDto.setUserName("sriman");
		AddressDto ad = new AddressDto();
		ad.setCity(city);
		VaccineStatusDto v = new VaccineStatusDto();
		v.setFirstDoseDate(new Date(20-04-2021));
		v.setSecondDoseDate(new Date(20-06-2021));
		v.setVaccinationStatus(2);
		v.setVaccineType(vaccineType);
		when(modelMapper.map(userDto, User.class)).thenReturn(user);
		doNothing().when(vaccineOperations).saveUser(user, city, vaccineType);
		vaccination.giveFirstDose(userDto,"22-04-2021" ,city, vaccineType);
		verify(vaccineOperations, times(1)).saveUser(user, city, vaccineType);
	}
	@Test
	void testFirstDose() {
		
	}

	@Test
	void testGiveSecondDose() {
		Long aadhar = 123412348074L;
		Date date = new Date(20-04-2021);
		doNothing().when(vaccineOperations).addSecondDoseDate(aadhar, date);
		vaccination.giveSecondDose(aadhar, "22-04-2021");
		verify(vaccineOperations, times(1)).addSecondDoseDate(aadhar, date);
	}

}
