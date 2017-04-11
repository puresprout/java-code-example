package number;

import java.text.NumberFormat;

import org.junit.Test;

public class NumberTest {

	@Test
	public void test() throws Exception {
		NumberFormat format = NumberFormat.getInstance();
		Number number = format.parse("200,000");
		long value = number.longValue();
		
		System.out.println((int) value);
	}
	
	@Test
	public void test2() throws Exception {
		NumberFormat format = NumberFormat.getInstance();
		Number number = format.parse("200,000");
		int value = number.intValue();
		
		System.out.println(value);
	}
	
}
