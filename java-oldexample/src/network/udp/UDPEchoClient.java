/*
 * UDPEchoClient.java
 *
 * Created on 2004년 2월 13일 (금), 오후 2:30
 */

package network.udp;

import java.net.*;
import java.io.*;
import java.util.Arrays;

/**
 *
 * @author  박성현
 */
public class UDPEchoClient {
    
    /** Creates a new instance of UDPEchoClient */
    public UDPEchoClient() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DatagramSocket ds = null;
        DatagramPacket sendPacket, recievePacket;
        byte[] buffer = new byte[256];
        
        // 만일 인수가 들어오지 않았다면
        if (args.length < 2) {
            System.out.println("java network.udp.UDPEchoClient (호스트이름) (포트번호)");
            System.exit(1);
        }
        
        System.out.println("전송할 데이터를 입력하세요");
        
        InetAddress host;   // 서버 주소
        int port;   // 서버 포트번호
        
        try {
            host = InetAddress.getByName(args[0]);
            port = Integer.parseInt(args[1]);
            
            ds = new DatagramSocket();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            String line;
            byte[] bytes;
            
            while (true)
            {
                line = br.readLine();   // 사용자 입력 저장
                
                if (line == null || line.length() == 0) {
                    System.out.println("프로그램을 종료했습니다");
                    break;  // 사용자 입력이 없으므로 나감
                }
                
                // 사용자가 입력한 내용 byte로 변환
                bytes = line.getBytes();
                
                // buffer를 null 로 채우기
                Arrays.fill(buffer, (byte) 0);
                
                // 사용자가 입력한 내용 버퍼에 넣기
                System.arraycopy(bytes, 0, buffer, 0, bytes.length > buffer.length ? buffer.length : bytes.length);
                
                // 송신용 패킷
                sendPacket = new DatagramPacket(buffer, buffer.length, host, port);
                
                // 송신
                ds.send(sendPacket);
                
                // 수신용 패킷
                recievePacket = new DatagramPacket(buffer, buffer.length);
                
                // 수신
                ds.receive(recievePacket);
                
                // 수신한 데이터 출력
                System.out.println("*받은 데이터 크기: " + recievePacket.getData().length + "\n*받은 데이터: " + new String(recievePacket.getData()).trim());
                System.out.println("*받은 주소와 포트: " + recievePacket.getAddress() + " " + recievePacket.getPort());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ds != null)
                    ds.close();
            } catch (Exception exception) {
            }
        }
    }
}