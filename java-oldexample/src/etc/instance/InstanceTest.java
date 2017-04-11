package etc.instance;

import java.text.DateFormat;

import javax.xml.parsers.SAXParserFactory;

public class InstanceTest {

	public static void main(String[] args) {
		DateFormat dateFormat = DateFormat.getDateInstance();
		DateFormat dateFormat2 = DateFormat.getDateInstance();

		System.out.println(dateFormat);
		System.out.println(dateFormat2);

		if (dateFormat == dateFormat2) {
			System.out.println("same.");
		} else {
			System.out.println("not same.");
		}

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParserFactory factory2 = SAXParserFactory.newInstance();

		System.out.println(factory);
		System.out.println(factory2);

		if (factory == factory2) {
			System.out.println("same.");
		} else {
			System.out.println("not same.");
		}

	}

}
