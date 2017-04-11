package digester;

import java.io.File;
import java.net.URL;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Test;

public class DigesterTest {

	// @Test
	public void testDigesterWithoutRulesFile() throws Exception {
		Digester digester = new Digester();
		digester.addObjectCreate("search", CastsInfo.class);
		digester.addObjectCreate("search/item", Cast.class);
		digester.addBeanPropertySetter("search/item/castId", "castId");
		digester.addBeanPropertySetter("search/item/castName", "castName");
		digester.addBeanPropertySetter("search/item/imageUrl", "imageUrl");
		digester.addBeanPropertySetter("search/item/updateDate", "updateDate");
		digester.addBeanPropertySetter("search/item/categoryName",
				"categoryName");
		digester
				.addBeanPropertySetter("search/item/readerCount", "readerCount");
		digester.addBeanPropertySetter("search/item/tags", "tags");
		digester.addSetNext("search/item", "addCast");

		File input = new File(
				"C:/Documents and Settings/�ڼ���/workspace/Test/src/digester/searchSample.xml");

		CastsInfo s = (CastsInfo) digester.parse(input);

		System.out.println(s.getCasts().size());

		for (int i = 0; i < s.getCasts().size(); i++) {
			System.out.println(ToStringBuilder.reflectionToString(s.getCasts()
					.get(i), ToStringStyle.MULTI_LINE_STYLE));

		}
	}

	@Test
	public void testDigesterWithRulesFile() throws Exception {
		File input = new File(
				"C:/Documents and Settings/�ڼ���/workspace/Test/src/digester/searchSample.xml");

		URL url = new URL(
				"file:///C:/Documents and Settings/�ڼ���/workspace/Test/src/digester/rules.xml");

		Digester digester = DigesterLoader.createDigester(url);

		CastsInfo s = (CastsInfo) digester.parse(input);

		System.out.println(s.getCasts().size());

		for (int i = 0; i < s.getCasts().size(); i++) {
			System.out.println(ToStringBuilder.reflectionToString(s.getCasts()
					.get(i), ToStringStyle.MULTI_LINE_STYLE));

		}
	}

}
