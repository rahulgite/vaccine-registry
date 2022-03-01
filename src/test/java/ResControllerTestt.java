import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import com.epam.vaccine_management.VaccineManagementSystemApplication;
import com.epam.vaccine_management.dto.UserDto;
import com.epam.vaccine_management.entities.User;
import com.epam.vaccine_management.service.ProceedDossage;
import com.epam.vaccine_management.service.Vaccination;
import com.epam.vaccine_management.service.Validations;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;

@SpringBootTest(classes = {VaccineManagementSystemApplication.class})
@AutoConfigureMockMvc
class ResControllerTestt {
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	Vaccination vaccination;
	
	@MockBean
	Validations validations;
	
	@MockBean
	ProceedDossage proceedDossage;

	
	@Test
	void getdosecount() throws Exception {
		when(vaccination.getDoseCount("covaxin")).thenReturn(77L);
		MvcResult result = mvc.perform(get("/find_dose_count")).andExpect(status().isOk()).andReturn();
		assertEquals(" no of vaccines available 77",result.getResponse().getContentAsString());
	}
	
	@Test
	void getusers() throws Exception {
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
		MvcResult result = mvc.perform(get("/find_all_users")).andExpect(status().isOk()).andReturn();
		assertEquals("list of people" + userList,result.getResponse().getContentAsString());
	}
	
	
	
	
	@Test
	void savePersonTest() throws Exception {
	UserDto userDto = new UserDto();
	userDto.setAadhar(123423453456L);
	userDto.setUserAge(19);
	userDto.setUserName("sriman");
	int covaxinAdded=77;
	String date="20-04-2021";
	String city = "bvrm";
	String jsonString = new ObjectMapper().writeValueAsString(userDto);
	when(vaccination.getDoseCount("covaxin")).thenReturn(9L);
	when(vaccination.isUserExists(123423453456L)).thenReturn(false);
	when(proceedDossage.firstDossage(any(UserDto.class), eq(date), eq(city), eq("covaxin"))).thenReturn("First dose completed");
	MvcResult result = mvc.perform(post("/save_people").param("city", "bvrm").param("date", "20-04-2021").param("vaccineType", "covaxin").contentType(MediaType.APPLICATION_JSON).content(jsonString)).andExpect(status().isOk()).andReturn();
	assertEquals("First dose completed",result.getResponse().getContentAsString());
	}	
	
	@Test
	void savePersonTestsecondDose() throws Exception {
	UserDto userDto = new UserDto();
	userDto.setAadhar(123423453456L);
	userDto.setUserAge(19);
	userDto.setUserName("sriman");
	int covaxinAdded=77;
	String vaccineType = "covaxin";
	long aadhar = 123412348074L;
	String date="20-04-2021";
	String city = "bvrm";
	String jsonString = new ObjectMapper().writeValueAsString(userDto);
	when(vaccination.getDoseCount("covaxin")).thenReturn(9L);
	when(vaccination.isUserExists(123423453456L)).thenReturn(true);
	when(proceedDossage.secondDossage(any(Long.class), eq(date), eq(vaccineType))).thenReturn("Second dose completed");
	when(vaccination.getVaccineType(aadhar)).thenReturn("covaxin");
//	when(vaccination.getVaccineStatus(aadhar)).thenReturn(1);
		
	MvcResult result = mvc.perform(post("/save_people").param("city", "bvrm").param("date", "20-04-2021").param("vaccineType", "covaxin").contentType(MediaType.APPLICATION_JSON).content(jsonString)).andExpect(status().isOk()).andReturn();
	assertEquals("",result.getResponse().getContentAsString());
	when(vaccination.getVaccineStatus(aadhar)).thenReturn(2);
	mvc.perform(post("/save_people").param("city", "bvrm").param("date", "20-04-2021").param("vaccineType", "covaxin").contentType(MediaType.APPLICATION_JSON).content(jsonString)).andExpect(status().isOk()).andReturn();
	}	
	
	@Test
	void updatePerson() throws Exception{
		String vaccineType = "covaxin";
		int covaxinAdded=77;
		String date="20-04-2021";
		doNothing().when(vaccination).setDoseCount(vaccineType,covaxinAdded, date);
		MvcResult result = mvc.perform(post("/update_count").param( "count","77" ).param("date", "20-04-2021")).andExpect(status().isOk()).andReturn();
		assertEquals("successful",result.getResponse().getContentAsString());
}
	


}

