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
public class Main2 {
    
    /** Creates a new instance of Main */
    public Main2() {
    }
    
    public static void main(String[] args) {
        Print2 p = new PrintBanner2("안녕");
        p.약하게출력하기();
        p.강하게출력하기();
    }
}
