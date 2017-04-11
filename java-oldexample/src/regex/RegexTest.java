package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexTest {

	@Test
	public void test() {
		String body = "	<div class='h5'>"
				+ "<h2>백광소재 <em>014580</em></h2>"
				+ "<div class='l2 tx2'>"
				+ "<span class='t12'>현재가</span> <em class='st1'>21,400</em> <span class='t12'>전일대비</span> <em class='st1 t13'><img src='http://static.naver.com/www/m/im/bl.gif' class='ic3 ic_s4' alt='상승' /><span class='hc'>상승</span> 250</em><em class='st1 t13'>(+1.18%)</em>"
				+ "</div></div>";
		
		String body2 = "	<div class='h5'>"
			+ "<h2>백광소재 <em>014580</em></h2>"
			+ "<div class='l2 tx2'>"
			+ "<span class='t12'>현재가</span> <em class='st1'><b>21,400</b></em> <span class='t12'>전일대비</span> <em class='st1 t13'><img src='http://static.naver.com/www/m/im/bl.gif' class='ic3 ic_s4' alt='상승' /><span class='hc'>상승</span> 250</em><em class='st1 t13'><b>(+1.18%)</b></em>"
			+ "</div></div>";
		
		obtainStockPrice2(body);
	}

	private void obtainStockPrice(String body) {
		// 처음 <em> 여는 태그부터 마직막 <em> 닫는 태그까지
		//Pattern p = Pattern.compile("<em .*</em>", Pattern.CASE_INSENSITIVE);
		
		// 각각의 <em> 태그 모두 추출. <em> 태그내에 또 다른 태그가 없어야 한다.
		Pattern p = Pattern.compile("<em [^<]*</em>", Pattern.CASE_INSENSITIVE);
		
		Matcher m = p.matcher(body);

		while (m.find()) {
			System.out.println(m.group());
		}
	}

	private void obtainStockPrice2(String body) {
		// 처음 <em> 여는 태그부터 마직막 <em> 닫는 태그까지
		//Pattern p = Pattern.compile("<em .*</em>", Pattern.CASE_INSENSITIVE);
		
		// 각각의 <em> 태그 모두 추출. <em> 태그내에 또 다른 태그가 없어야 한다.
		Pattern p = Pattern.compile("<em [^<]*</em>", Pattern.CASE_INSENSITIVE);
		
		Matcher m = p.matcher(body);

		if (m.find()) {
			String value = m.group();
			
			System.out.println(value);
			
			int start = value.indexOf(">");
			int end = value.indexOf("</");
			
			System.out.println(value.substring(start + 1, end));
		}
	}

}
