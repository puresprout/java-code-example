/*
 * ClassLoading.java
 *
 * Created on 2004년 3월 16일 (화), 오전 11:21
 *
 * 이 프로그램은 클래스 경로에 없는 클래스를 어떻게 로딩 하는지 보여준다.
 * Hello 는 인터페이스이다. 이 인터페이스를 구현하는 모든 클래스는 반드시 toHello() 메소드를 구현해야 한다.
 * HelloImpl은 Hello 인터페이스를 구현하는 클래스이며 toHello 메소드를 정의하고 있다. 단순하게 "박성현이의 헬로우" 라는 메소드를 리턴한다.
 * URLClassLoader 를 통해 HelloImpl 클래스를 로드하고 그 로드된 클래스를 Hello 인터페이스 참조 변수에 담는다.
 * Hello 인터페이스 참조변수를 통해 toHello 메소드를 실행하면 실제 로드된 구현 클래스의 toHello 메소드가 호출된게 된다.
 * 이것이 객체지향 개념의 다형성이다.
 * 만일 구현 클래스가 HelloImpl2 가 되던지 달라지더라도 우리는 동일하게 Hello 인터페이스 참조 변수를 가지고 작업하면 다형성의 원리에 의해 실제
 * 구현 클래스의 메소드가 자동 호출된다.
 */

package dynamicLoading;

import java.net.*;

/**
 *
 * @author  박성현
 */
public class ClassLoading {
    
    /** Creates a new instance of ClassLoading */
    public ClassLoading() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        URL[] urls = null;
        
        try {
            URL url = new URL("file:/./");  // 현재 디렉토리에 대한 Url 경로. 반드시 '/' 로 끝나야만 된다.
            urls = new URL[]{url};
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        
        try {
            // URLClassLoader 는 Jar 파일들과 디렉토리를 가리키는 URL 들의 경로로부터 클래스나 리소스를 로딩하는데 사용된다.
            // '/'로 끝나는 URL은 디렉토리로 간주되고 그렇지 않으면 Jar로 간주된다.
            ClassLoader cl = new URLClassLoader(urls);
            
            Class cls = cl.loadClass("dynamicLoading.HelloImpl");
            
            Hello myObj = (Hello) cls.newInstance();    // 인스턴스를 생성하면서 형을 Hello 인터페이스로 변환한다.
            
            System.out.println(myObj.toHello());
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
