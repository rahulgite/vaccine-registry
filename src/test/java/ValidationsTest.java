

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.epam.vaccine_management.service.Validations;

class ValidationsTest {
	Validations validations = new Validations();

	@Test
	void isAadharTest() {
		assertEquals(true, validations.isAadharValid(123412341234L));
	}

	@Test
	void isAgeTest() {
		assertEquals(true, validations.isAgeValid(19));
	}

	@Test
	void isNameTest() {
		assertEquals(true, validations.isNameValid("sriman"));
	}

	@Test
	void isDurationTest() {
		String sdate1 = "20-04-2021";
		Date date1;
		String sdate2 = "20-06-2021";
		Date date2;
		while (true) {
			try {
				date1 = new SimpleDateFormat("dd-MM-yyyy").parse(sdate1);
				date2 = new SimpleDateFormat("dd-MM-yyyy").parse(sdate2);
				break;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		assertTrue(validations.isDurationValid(date1, date2));
	}

	@Test
	void falseAadharTest() {
		assertEquals(false, validations.isAadharValid(12341234124L));
	}

	@Test
	void falseAgeTest() {
		assertEquals(false, validations.isAgeValid(9));
	}

	@Test
	void falseNameTest() {
		assertEquals(false, validations.isNameValid("sriman9"));
	}

	@Test
	void falseDurationTest() {
		assertEquals(false, validations.isDurationValid(new Date(20 - 06 - 2021), new Date(20 - 04 - 2021)));
	}

}
