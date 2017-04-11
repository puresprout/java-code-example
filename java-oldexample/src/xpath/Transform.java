package xpath;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Transform {

	public static void main(String[] args) throws Exception {
		Source xmlSource = new StreamSource("test/xpath/Books.xml");
		Source xsltSource = new StreamSource("test/xpath/BooksXSLT.xsl");
		StreamResult result = new StreamResult(System.out);

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = tf.newTransformer(xsltSource);
		t.setOutputProperty(OutputKeys.ENCODING, "euc-kr");
		t.transform(xmlSource, result);
	}
}
