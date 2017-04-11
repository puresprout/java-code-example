/*
 * PrintBanner2.java
 *
 * Created on 2004년 2월 13일 (금), 오전 10:42
 */

package designPattern.adapter;

/**
 *
 * @author  박성현
 */
public class PrintBanner2 extends Print2 {
    
    private Banner banner;
    
    /** Creates a new instance of PrintBanner2 */
    public PrintBanner2(String string) {
        this.banner = new Banner(string);
    }
    
    public void 약하게출력하기() {
        banner.괄호로감싸서출력하기();
    }
    
    public void 강하게출력하기() {
        banner.별표로감싸서출력하기();
    }
    
}
