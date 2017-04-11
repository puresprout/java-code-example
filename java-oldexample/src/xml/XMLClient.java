/*
 * XMLClient.java
 *
 * Created on 2004년 5월 17일 (월), 오후 12:57
 */

package xml;

import java.net.*;
import java.io.*;
import javax.xml.parsers.*; // XML Parser 를 위해
import javax.xml.transform.*;   // 변환을 위해
import javax.xml.transform.dom.*;   // DOM 객체에 대한 원본과 목적지
import javax.xml.transform.stream.*;    // 스트림에 대한 원본과 목적지
import org.w3c.dom.*;   // DOM 객체을 위해

/**
 *
 * @author  박성현
 */
public class XMLClient {
    
    /** Creates a new instance of XMLClient */
    public XMLClient() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            Socket client = new Socket("localhost", 1777);  // 서버쪽과 연결 시도
        
            InputStream is = client.getInputStream();   // 연결된 소켓에서 입력 스트림 얻기
        
            // Parser 공장얻기. new 로 생성하지 않는 이유는 JVM 상에서 단 하나의 파서 공장을 얻어 공유할려는 목적
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        
            // 파서 공장에서 파서(DocumentBuilder) 하나를 얻는다.
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            
            // parsing. parse의 인수는 소켓의 입력 스트림. 즉 소켕에서 데이터를 읽어 DOM 객체 생성
            Document document = dBuilder.parse(is);
        
            // DOM 객체를 화면에 출력하기 위해선 변환이 필요하다.
            TransformerFactory tFactory = TransformerFactory.newInstance();
	
            Transformer transformer = tFactory.newTransformer();
			
            // 변환의 소스
            DOMSource source = new DOMSource(document);

            // 변환의 목적지
            StreamResult result = new StreamResult(System.out);
			
            // 변환시 encoding 설정
            transformer.setOutputProperty(OutputKeys.ENCODING, "euc-kr");
            
            // 변환. source인 DOM 객체를 result인 System.out 에 출력
            transformer.transform(source, result);
            
            is.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}