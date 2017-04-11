/*
 * XMLServer.java
 *
 * Created on 2004년 5월 17일 (월), 오후 12:41
 */

package xml;

import java.net.*;  // 소켓을 위해
import java.io.*;   // 소켓에서 입출력

/**
 *
 * @author  박성현
 */
public class XMLServer {
    
    /** Creates a new instance of XMLServer */
    public XMLServer() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            ServerSocket acceptSocket = new ServerSocket(1777); // 1777 포트에서 연결을 받아 들이는 소켓
        
            System.out.println("Listening at port 1777");
            Socket server = acceptSocket.accept();  // 클라이언트에서 연결할 때까지 블락(block)됨. 리턴값은 클라이언트와 연결된 연결소켓(서버쪽)
            System.out.println("Accepted client socket");
        
            // 소켓으로 데이터를 출력하기 위해 출력스트링 얻기.
            // OutputStream 은 바이트(1byte) 스트림. OutputStreamWriter 는 캐릭터(2byre) 스트림. PrintWriter 스트림은 String 데이터를 보낼수 있는 고급 스트림
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(server.getOutputStream()));

            // 출력 스트림에 XML 데이터를 출력(String 타입)
            pw.write("<?xml version=\"1.0\" encoding=\"euc-kr\"?><languages>\n\t<lowLevel>\n\t\t<item>Assembly</item>\n\t</lowLevel>\n\t<middleLevel>\n\t\t<item>C</item>\n\t</middleLevel>\n\t<highLevel>\n\t\t<item>C#</item>\n\t\t<item>자바</item>\n\t</highLevel>\n</languages>");
            
            // 닫기
            pw.close();
            server.close();
            acceptSocket.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}