

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.epam.vaccine_management.entities.VaccineStatus;

class VaccineStatusTest {
	VaccineStatus vaccineStatus = new VaccineStatus();

	@Test
	void getFirstDoseDateTest() {
		vaccineStatus.setFirstDoseDate(new Date(20 - 04 - 2021));
		assertEquals(new Date(20 - 04 - 2021), vaccineStatus.getFirstDoseDate());
	}

	@Test
	void getSecondDoseDateTest() {
		vaccineStatus.setSecondDoseDate(new Date(20 - 06 - 2021));
		assertEquals(new Date(20 - 06 - 2021), vaccineStatus.getSecondDoseDate());
	}

	@Test
	void getFirstDateVaccineTest() {
		vaccineStatus.setFirstDoseDate(new Date(20 - 04 - 2021));
		assertEquals(0, vaccineStatus.getVaccinationStatus());
	}

	@Test
	void getVaccinationStatus() {
		vaccineStatus.setSecondDoseDate(new Date(20 - 06 - 2021));
		assertEquals(0, vaccineStatus.getVaccinationStatus());
	}

	@Test
	void getVaccineType() {
		String vaccineType = "covaxin";
		vaccineStatus.setVaccineType(vaccineType);
		assertEquals(vaccineType, vaccineStatus.getVaccineType());
	}

	@Test
	void getVaccineStatus() {
		vaccineStatus.setVaccinationStatus(2);
		assertEquals(2, vaccineStatus.getVaccinationStatus());
	}

	@Test
	void testGetId() {
		vaccineStatus.setId(2);
		assertEquals(2, vaccineStatus.getId());
	}

	@Test
	void getVaccineStatusTest() {
		assertEquals(0, vaccineStatus.getVaccinationStatus());
	}

}
