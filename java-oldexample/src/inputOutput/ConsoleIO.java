/*
 * ConsoleIO.java
 *
 * Created on 2004년 8월 19일 (목), 오후 5:6
 */

package inputOutput;

import java.io.*;

/**
 *
 * @author  박성현
 */
public class ConsoleIO {
    
    /** Creates a new instance of ConsoleIO */
    public ConsoleIO() {
    }
    
    public static void main(String[] arg) {
        try {
/*            
            // 콘솔로부터 1바이트를 읽어 들인다. 처음에 버퍼에 데이터가 없을 땐 블록된다. 엔터를 치게 되면 비로소 읽어들인다.
            int data = System.in.read();
            System.out.println((char) data);   // date 를 그냥 출력하면 int형이므로 숫자(ASCII 코드값)가 출력된다. 그래서 char 형으로 캐스팅 혜야한다.
            
            // 버퍼에 데이터가 있으면 바로 읽어들인다.
            // 참고로 엔터키는 ASCII 코드로 10 이 들어온다.
            // 만일 한글을 입력하게 되면 2 바이트이므로 한 바이트씩 읽게 되고 한 바이트씩 따로 따로 출력된다. 그래서 바이트 스트림이 아닌 캐릭터 스트림이 필요하다.
            data = System.in.read();
            System.out.println((char) data);
 */
            
            // 콘솔 입력인 System.in 스트림은 바이트 단위이고 이를 자바에서 처리하기 위해 char 형에 담기 위해서는 캐릭터 스트림이 필요하다.
            // 따라서 System.in 을 InputStramReader 로 통해 캐릭터 스트림으로 변환하고 한 줄씩 읽기 위해 LineNumberReader 스트림으로 감쌌다.
            LineNumberReader in = new LineNumberReader(new InputStreamReader(System.in));
            
            // readLine() 메소드는 한 줄을 읽되 '\n' 은 뺀다.
            String data = in.readLine();
            System.out.println(data);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
}
