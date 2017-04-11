package xpath;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XPathSearch {
	public static void main(String[] args) throws Exception {
		InputSource is = new InputSource("test/xpath/Books.xml");

		XPathFactory factory = XPathFactory.newInstance();
		XPath path = factory.newXPath();

		// 1. 리턴타입을 STRING으로 한 경우
		String id = (String) path.evaluate("/Catalog/Book/@id", is, XPathConstants.STRING);
		System.out.println("첫번째 책의 id " + id);
		System.out.println();

		// 2. 리턴타입을 NUMBER로 한 경우
		int bookCount = ((Double) path.evaluate("count(//Book)", is, XPathConstants.NUMBER))
				.intValue();
		System.out.println("총개수 " + bookCount);
		System.out.println();

		// 3. 리턴타입을 NODESET으로 한 경우
		NodeList list = (NodeList) path.evaluate("/Catalog/Book/Title", is, XPathConstants.NODESET);
		for (int i = 0; i < list.getLength(); i++) {
			System.out.println("책 제목 " + list.item(i).getTextContent());
		}
	}
}
