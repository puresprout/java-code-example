package serialization;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 총 세가지의 직렬화 예를 보여준다.<br>
 * 1번 Count 클래스 객체 직렬화 예<br>
 * 2번 NotSerializableCount 클래스가 Serializable 인터페이스를 구현하지 않았으므로 직렬화 불가 예<br>
 * 3번 직렬화하지 않을려면 transient 키워드로 지정 예
 * 
 * @author 박성현
 * 
 */
public class Serialization implements Serializable {

	/* 1번 시작 */
	private Count count = new Count();
	/* 1번 끝 */

	/* 2번 시작 */
	// private NotSerializableCount notSerializableCount = new
	// NotSerializableCount();
	/* 2번 끝 */

	/* 3번 시작 */
	// transient private NotSerializableCount notSerializableCount = new
	// NotSerializableCount();
	/* 3번 끝 */

	public static void main(String[] args) {
		Serialization serialization = new Serialization();

		try {
			FileOutputStream fos = new FileOutputStream("object.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			/* 1번 시작 */
			serialization.count.setDownload(100);
			serialization.count.setExecution(2000);
			serialization.count.remove = 5;

			oos.writeObject(serialization.count);
			/* 1번 끝 */

			/* 2번 시작 */
			// oos.writeObject(serialization);
			/* 2번 끝 */
			
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class NotSerializableCount {

}
