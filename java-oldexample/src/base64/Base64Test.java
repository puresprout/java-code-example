package base64;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class Base64Test {

	@Test
	public void testBase64() throws Exception {
		Base64 b = new Base64();

		byte[] bs = b.encode("admin:fjghty56".getBytes());

		for (int i = 0; i < bs.length; i++) {
			System.out.print((char) bs[i]);
		}

		System.out.println();

		String s = "YWRtaW46ZmpnaHR5NTY=";

		byte[] bs2 = b.decode(s.getBytes());

		for (int i = 0; i < bs2.length; i++) {
			System.out.print((char) bs2[i]);
		}
	}

}
