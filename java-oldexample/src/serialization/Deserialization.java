package serialization;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Deserialization {

	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("object.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);

			Count count = (Count) ois.readObject();

			System.out.println("download " + count.getDownload());
			System.out.println("execution " + count.getExecution());
			System.out.println("execution " + count.remove);

			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
