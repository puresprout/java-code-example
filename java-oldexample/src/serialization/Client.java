package serialization;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", 1234);
			ObjectOutputStream oos = new ObjectOutputStream(socket
					.getOutputStream());

			for (int i = 0; i < 10; i++) {
				Count count = new Count();
				count.setDownload(i * 2);
				count.setExecution(i * i);
				count.remove = i;

				oos.writeObject(count);
				
				Thread.sleep(1000 * 3);
			}

			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
