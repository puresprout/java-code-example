/*
 * Banner.java
 *
 * Created on 2004년 2월 13일 (금), 오전 10:7
 */

package designPattern.adapter;

/**
 *
 * @author  박성현
 */
public class Banner {
    
    private String string;
    
    /** Creates a new instance of Banner */
    public Banner(String string) {
        this.string = string;
    }
    
    public void 괄호로감싸서출력하기() {
        System.out.println("(" + string + ")");
    }
    
    public void 별표로감싸서출력하기() {
        System.out.println("*" + string + "*");
    }
    
}
