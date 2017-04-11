package string;

public class StringTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "첫줄\r\n두번째줄";

		System.out.println(str);

		for (int i = 0; i < str.length(); i++) {
			System.out.println(i + " : " + (int) str.charAt(i) + " : "
					+ str.charAt(i));
		}
		
		String cStr = str.replace("\r\n", "<br>");
		
		System.out.println(cStr);
	}

}
