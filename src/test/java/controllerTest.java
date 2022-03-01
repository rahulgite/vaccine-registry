

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.epam.vaccine_management.VaccineManagementSystemApplication;
import com.epam.vaccine_management.dto.UserDto;
import com.epam.vaccine_management.entities.User;
import com.epam.vaccine_management.entities.VaccineStatus;
import com.epam.vaccine_management.exceptions.ResourceNotFoundException;
import com.epam.vaccine_management.service.ProceedDossage;
import com.epam.vaccine_management.service.Vaccination;
import com.epam.vaccine_management.service.Validations;
@SpringBootTest(classes= {VaccineManagementSystemApplication.class})
@AutoConfigureMockMvc
class controllerTest {
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	Vaccination vaccination;
	
	@MockBean
	Validations validations;
	
	@MockBean
	ProceedDossage proceedDossage;
	
	@Test
	void updatePerson() throws Exception{
		String vaccineType = "covaxin";
		int covaxinAdded=77;
		String date="20-04-2021";
		doNothing().when(vaccination).setDoseCount(vaccineType,covaxinAdded, date);
		mvc.perform(post("/updatecount").param("covaxinAdded" , "77").param("date", "20-04-2021")).andExpect(view().name("success")).andExpect(model().attribute("message", "doses added" ));
	}
	
	
	
	@Test
	void findAllUsers() throws Exception{
		List<User> userList = new ArrayList<>();// = vaccination.getUsersDetails();
		User user1 = new User();
		User user2 = new User();
		user1.setAadhar(123412341234L);
		user1.setUserAge(19);
		user1.setUserName("sri");
		user2.setAadhar(123412348074L);
		user2.setUserAge(20);
		user2.setUserName("Sriman");
		userList.add(user2);
		userList.add(user1);
		when(vaccination.getUsersDetails()).thenReturn(userList);
		mvc.perform(get("/findallusers")).andExpect(view().name("success")).andExpect(model().attribute("message","[User [aadhar=123412348074, userAge=20, userName=Sriman], User [aadhar=123412341234, userAge=19, userName=sri]]"));
	}

	
	@Test
	void findDoseCount() throws Exception{
		when(vaccination.getDoseCount("covaxin")).thenReturn(77L);
		mvc.perform(get("/find_dosecount")).andExpect(view().name("success")).andExpect(model().attribute("message", "no of vaccines available 77"));
		
	}
	
	@Test
	void savePeople() throws Exception{
		UserDto userDto = new UserDto();
		userDto.setAadhar(123412348074L);
		userDto.setUserAge(19);
		userDto.setUserName("sriman");
		String vaccineType = "covaxin";
		int covaxinAdded=77;
		String date="20-04-2021";
		String city = "bvrm";
		long aadhar = 123412348074L;
		when(vaccination.getDoseCount("covaxin")).thenReturn(9L);
		when(vaccination.isUserExists(aadhar)).thenReturn(false);
		when(proceedDossage.firstDossage(any(UserDto.class), eq(date),eq(city), eq(vaccineType))).thenReturn("First dose completed");
		mvc.perform(post("/savepeople").param("userAge", "19").param("userName", "sriman").param("aadhar", "123412348074").param("city", "bvrm").param("date", "20-04-2021").param("vaccineType", "covaxin")).andExpect(view().name("success")).andExpect(model().attribute("message","First dose completed"));
	
	}
	
	@Test
	void savePeoplesecondDose() throws Exception{
		UserDto userDto = new UserDto();
		userDto.setAadhar(123412348074L);
		userDto.setUserAge(19);
		userDto.setUserName("sriman");
		String vaccineType = "covaxin";
		int covaxinAdded=77;
		String date="20-04-2021";
		String city = "bvrm";
		long aadhar = 123412348074L;
		when(vaccination.getDoseCount("covaxin")).thenReturn(9L);
		when(vaccination.isUserExists(aadhar)).thenReturn(true);
		when(proceedDossage.secondDossage(any(Long.class), eq(date), eq(vaccineType))).thenReturn("Second dose completed");
		when(vaccination.getVaccineType(aadhar)).thenReturn("covaxin");
		mvc.perform(post("/savepeople").param("userAge", "19").param("userName", "sriman").param("aadhar", "123412348074").param("city", "bvrm").param("date", "20-04-2021").param("vaccineType", "covaxin")).andExpect(view().name("success")).andExpect(model().attribute("message","Second dose completed"));
	
		when(vaccination.getVaccineStatus(aadhar)).thenReturn(2);
		mvc.perform(post("/savepeople").param("userAge", "19").param("userName", "sriman").param("aadhar", "123412348074").param("city", "bvrm").param("date", "20-04-2021").param("vaccineType", "covaxin")).andExpect(view().name("success")).andExpect(model().attribute("message","Vaccination already completed"));
		
		
	}
	@Test
	void firstDoseException() throws Exception{
		UserDto userDto = new UserDto();
		userDto.setAadhar(123412348074L);
		userDto.setUserAge(19);
		userDto.setUserName("sriman");
		String vaccineType = "covaxin";
		int covaxinAdded=77;
		String date="20-04-2021";
		String city = "bvrm";
		long aadhar = 123412348074L;
		when(vaccination.getDoseCount("covaxin")).thenReturn(0L);
//		when(proceedDossage.secondDossage(123412348074L, date, vaccineType)).thenReturn("Second dose completed");
		mvc.perform(post("/savepeople").param("userAge", "19").param("userName", "sriman").param("aadhar", "123412348074").param("city", "bvrm").param("date", "20-04-2021").param("vaccineType", "covaxin"));
	
	}
	
	
}
