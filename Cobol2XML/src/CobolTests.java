import static org.junit.Assert.*;

import org.junit.Test;

import cobol.Cobol;

public class CobolTests {

	@Test
	public void cobolTests() {
		Cobol c = new Cobol();
		c.setProgram_ID("1123");
		c.setDivisionName("YMN");
		c.setSectionName("test");
		
		assertEquals(c.toString(), "1123, YMN, test");
		
		c.setProgram_ID("1124");
		c.setDivisionName("YMN");
		c.setSectionName("test");
		
		assertNotEquals(c.toString(), "1123, YMN, test");
	}

}
