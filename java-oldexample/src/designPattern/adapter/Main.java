/*
 * Main.java
 *
 * Created on 2004년 2월 13일 (금), 오전 10:14
 */

package designPattern.adapter;

/**
 *
 * @author  박성현
 */
public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    public static void main(String[] args) {
        Print p = new PrintBanner("안녕");
        p.약하게출력하기();
        p.강하게출력하기();
    }
}
