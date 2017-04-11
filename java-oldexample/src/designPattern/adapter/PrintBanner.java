/*
 * PrintBanner.java
 *
 * Created on 2004년 2월 13일 (금), 오전 10:12
 */

package designPattern.adapter;

/**
 *
 * @author  박성현
 */
public class PrintBanner extends Banner implements Print {
    
    /** Creates a new instance of PrintBanner */
    public PrintBanner(String string) {
        super(string);
    }
    
    public void 강하게출력하기() {
        별표로감싸서출력하기();
    }
    
    public void 약하게출력하기() {
        괄호로감싸서출력하기();
    }
    
}
