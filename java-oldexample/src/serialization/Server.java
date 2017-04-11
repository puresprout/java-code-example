package serialization;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		new Server().go();
	}

	public class ClientHandler implements Runnable {
		
		ObjectInputStream ois;
		Socket clientSocket;

		public ClientHandler(Socket socket) {
			try {
				clientSocket = socket;
				ois = new ObjectInputStream(clientSocket.getInputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void run() {
			Object obj;

			try {
				while ((obj = ois.readObject()) != null) {
					Count count = (Count) obj;

					System.out.println("download " + count.getDownload());
					System.out.println("execution " + count.getExecution());
					System.out.println("execution " + count.remove);
					System.out.println();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void go() {
		try {
			ServerSocket serverSocket = new ServerSocket(1234);

			while (true) {
				Socket clientSocket = serverSocket.accept();

				Thread t = new Thread(new ClientHandler(clientSocket));
				t.start();

				System.out.println("연결됨.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
