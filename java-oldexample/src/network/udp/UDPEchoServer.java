/*
 * UDPEchoServer.java
 *
 * Created on 2004년 2월 13일 (금), 오후 2:15
 */

package network.udp;

import java.net.*;

/**
 *
 * @author  박성현
 */
public class UDPEchoServer {
    
    /** Creates a new instance of UDPEchoServer */
    public UDPEchoServer() {
    }
    
    public static void main(String[] args) {
        DatagramSocket ds = null;   // 소켓
        DatagramPacket sendPacket, recievePacket;   // 송수신 패킷
        byte[] buffer = new byte[256];  // 버퍼
        
        System.out.print("서버는 클라이언트의 메시지를 받아 그 메시지를 반송합니다.");
        System.out.print("UDP 통신이므로 연결을 유지할 필요도 없고 각각의 클라이언트마다 서버소켓을 만들 필요도 없습니다.");
        System.out.println("하지만 메시지와 메시지 순서를 100% 신뢰할 수는 없습니다.");
        System.out.println("이 서버는 UDP 통신을 사용하며, 포트는 7701번을 이용합니다.");
        System.out.println("프로그램을 끝내시려면 [Ctrl+C] 키를 누르십시오.");
        
        try {
            // 서버쪽은 클라이언트가 보내는 포트번호쪽을 알고 있어야 한다.
            ds = new DatagramSocket(7701);
            
            while (true)
            {
                // 수신용 패킷
                recievePacket = new DatagramPacket(buffer, buffer.length);
                
                // 수신
                ds.receive(recievePacket);
                
                // 송신용 패킷
                sendPacket = new DatagramPacket(buffer, buffer.length, recievePacket.getAddress(), recievePacket.getPort());
                
                // 송신
                ds.send(sendPacket);
                
                // 받고 보낸 메시지에 대한 정보
                System.out.println("*받고 보낸 메시지: " + new String(sendPacket.getData()).trim());
                System.out.println("*받은 주소와 포트: " + recievePacket.getAddress() + " " + recievePacket.getPort());
                System.out.println("*보낸 주소와 포트: " + sendPacket.getAddress() + " " + sendPacket.getPort());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (ds != null) ds.close();
            } catch (Exception e) {
            }
        }
    }
}
