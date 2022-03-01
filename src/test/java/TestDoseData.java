

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Date;
import org.junit.jupiter.api.Test;
import com.epam.vaccine_management.entities.DoseData;

class TestDoseData {

	DoseData d;
	@Test
	void testGetCovaxinAdded() {
		d = new DoseData();
		d.setCovaxinAdded(77);
		assertEquals(77, d.getCovaxinAdded());
	}
	@Test
	void testGetDate() {
		d = new DoseData();
		Date date = new Date(20-04-2021);
		d.setDate(date);
		assertEquals(date, d.getDate());
	}

}
