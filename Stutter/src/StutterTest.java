import static org.junit.Assert.*;

import org.junit.Test;

public class StutterTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	
	public void testisDelimitNull(char testChar) {
		boolean result = Stutter.isDelimit((char) 0);
		assertFalse(result);
	}
	

}
