package file;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 파일명속에 있는 숫자를 포맷팅해서 파일명을 변경한다.
 * 예)
 * A 1.mp3 -> A 01.mp3
 * A 10.mp3 -> A 10.mp3
 * 
 * 현재는 쉬운성경 폴더에 있는 파일에만 적용할 수 있다.
 * 
 * @author 박성현
 *
 */
public class Rename {

	private static MyFilter filter = new MyFilter();

	public static void main(String[] str) throws Exception {
		File f = new File(str[0]);
		// File f = new File("C:/Download/쉬운성경");

		process(f.listFiles(filter));
	}

	public static void process(File[] files) {
		int max = 0;

		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				process(files[i].listFiles(filter));
			} else {
				String parent = files[i].getParentFile().getName();
				String name = files[i].getName();
				// System.out.println(parent + " | " + name);

				int s = name.indexOf(parent) + parent.length() + 1;
				int e = name.indexOf("-", s);
				if (e == -1) {
					e = name.indexOf(".", s);
				}

				String number = name.substring(s, e);

				// System.out.println(name + " | " + number + " | " +
				// number.length());

				if (number.length() > max) {
					max = number.length();
				}
			}
		}

		// System.out.println("max: " + max);

		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				String parent = files[i].getParentFile().getName();
				String name = files[i].getName();
				// System.out.println(parent + " | " + name);

				int s = name.indexOf(parent) + parent.length() + 1;
				int e = name.indexOf("-", s);
				if (e == -1) {
					e = name.indexOf(".", s);
				}

				String front = name.substring(0, s);

				String number = name.substring(s, e);

				String back = name.substring(e);

				boolean b = false;

				for (int j = number.length(); j < max; j++) {
					number = "0" + number;

					b = true;
				}

				String newName = front + number + back;

				// System.out.println(newName);

				if (b == true) {
					File newFile = new File(files[i].getParent() + "/"
							+ newName);

					System.out.println(newFile.getAbsolutePath());

					files[i].renameTo(newFile);
				}
			}
		}
	}

}

class MyFilter implements FilenameFilter {

	public boolean accept(File file, String name) {
		if (file.isDirectory()) {
			return true;
		}

		if (name.toLowerCase().endsWith(".mp3")) {
			return true;
		}

		if (name.toLowerCase().endsWith(".txt")) {
			return true;
		}

		return false;
	}

}