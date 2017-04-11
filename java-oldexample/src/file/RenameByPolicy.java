package file;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;

/**
 * 파일을 파일명 변경 정책에 따라 복사한다.
 * 
 * @author 박성현
 * 
 */
public class RenameByPolicy {

	enum CONVERSION_TYPE {
		NO_CONVERSTION, ONLY_NUMBER, ONLY_ENGLISH, NUMBER_AND_ENGLISH
	};

	private boolean realCopy = false;

	/**
	 * 파일명 변경정책은 확장자를 제외한 파일명에만 적용된다.
	 */
	private CONVERSION_TYPE conversionType = CONVERSION_TYPE.NO_CONVERSTION;

	/**
	 * 파일명 변경정책이 ONLY_NUMBER 일때 숫자를 포맷팅하는지 여부 예) 1.mp3 -> 01.mp3 10.mp3 ->
	 * 10.mp3
	 */
	private boolean isNumberFormatting = true;

	/**
	 * 포맷핑 자리수
	 */
	private int positionNumber = 2;

	@Test
	@Ignore
	public void renameReal() throws Exception {
		realCopy = true;
		conversionType = CONVERSION_TYPE.ONLY_NUMBER;
		positionNumber = 2;

		String src = "D:/임시/기적의 영어 동화";
		String dest = "d:/temp";

		rename(src, dest);
	}

	@Test
	@Ignore
	public void renameFileTest() throws Exception {
		String src = "D:/임시/기적의 영어 동화/80일간의 세계일주 Around the World in Eighty Days/Around the World in Eighty Days 1.mp3";
		String dest = "d:/temp";

		rename(src, dest);
	}

	@Test
	@Ignore
	public void renameFileTest2() throws Exception {
		conversionType = CONVERSION_TYPE.ONLY_NUMBER;

		String src = "D:/임시/기적의 영어 동화/80일간의 세계일주 Around the World in Eighty Days/Around the World in Eighty Days 1.mp3";
		String dest = "d:/temp";

		rename(src, dest);
	}

	@Test
	@Ignore
	public void renameDirTest() throws Exception {
		String src = "D:/임시/기적의 영어 동화/80일간의 세계일주 Around the World in Eighty Days";
		String dest = "d:/temp";

		rename(src, dest);
	}

	@Test
	@Ignore
	public void renameDirTest2() throws Exception {
		String src = "D:/임시/기적의 영어 동화";
		String dest = "d:/temp";

		rename(src, dest);
	}

	/**
	 * 소스를 목적지 폴더로 복사한다. 폴더는 폴더명 변경없이 그대로 복사되고, 파일은 파일명 변경 정책에 따라 새로운 파일명으로
	 * 복사된다.
	 * 
	 * @param src
	 *            소스. 존재하는 폴더이거나 파일이어야 한다.
	 * @param destDir
	 *            목적지 폴더. 존재하지 않으면 만들어진다.
	 * @throws Exception
	 */
	private void rename(String src, String destDir) throws Exception {
		File srcFile = new File(src);
		if (!srcFile.exists()) {
			System.out.println("src doesn't exist.");
			return;
		}

		// dest 폴더 만들기
		File destFile = new File(destDir);
		if (!destFile.exists()) {
			if (realCopy) {
				destFile.mkdirs();
			}
		}

		renameInternal(srcFile, destFile);
	}

	private void renameInternal(File srcFile, File destFolder) throws Exception {
		if (srcFile.isDirectory()) {
			File destChildFolder = new File(destFolder.getAbsolutePath() + "/"
					+ srcFile.getName());
			if (!destChildFolder.exists()) {
				if (realCopy) {
					destChildFolder.mkdir();
				}
			}

			File[] children = srcFile.listFiles();

			for (int i = 0; i < children.length; i++) {
				renameInternal(children[i], destChildFolder);
			}
		} else {
			String englishNameFile = getConvertedName(srcFile.getName());

			File destFile = new File(destFolder.getAbsolutePath() + "/"
					+ englishNameFile);

			System.out.println("copy " + srcFile.getAbsolutePath() + " to "
					+ destFile.getAbsolutePath());

			if (realCopy) {
				try {
					FileUtils.copyFile(srcFile, destFile);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private String getConvertedName(String name) {
		if (conversionType == CONVERSION_TYPE.NO_CONVERSTION) {
			return name;
		} else {
			String fileNameExceptExt = name;
			String ext = "";

			int index = name.lastIndexOf(".");
			if (index != -1) {
				fileNameExceptExt = name.substring(0, index);
				ext = name.substring(index);

				// System.out.println(fileNameExceptExt + " " + ext);
			}

			String convertedName = "";

			for (int i = 0; i < fileNameExceptExt.length(); i++) {
				char c = fileNameExceptExt.charAt(i);

				if (conversionType == CONVERSION_TYPE.ONLY_NUMBER) {
					if (containedNumber(c)) {
						convertedName += c;
					}
				} else if (conversionType == CONVERSION_TYPE.ONLY_ENGLISH) {
					if (containedEnglish(c)) {
						convertedName += c;
					}
				} else if (conversionType == CONVERSION_TYPE.NUMBER_AND_ENGLISH) {
					if (containedNumber(c) || containedEnglish(c)) {
						convertedName += c;
					}
				}
			}

			if (conversionType == CONVERSION_TYPE.ONLY_NUMBER
					&& isNumberFormatting) {
				try {
					convertedName = String.format("%0" + positionNumber + "d",
							Integer.parseInt(convertedName));
					// System.out.println(convertedName);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			return convertedName + ext;
		}
	}

	private boolean containedNumber(char c) {
		// System.out.println(c + " " + (int) c);

		if (c >= '0' && c <= '9') {
			return true;
		} else if (c == '.') {
			return true;
		}

		return false;
	}

	private boolean containedEnglish(char c) {
		// System.out.println(c + " " + (int) c);

		if (c >= 'a' && c <= 'z') {
			return true;
		} else if (c >= 'A' && c <= 'Z') {
			return true;
		} else if (c == '.') {
			return true;
		}

		return false;
	}

	@Test
	@Ignore
	public void printAscii() {
		for (int i = 0; i < 128; i++) {
			System.out.println(i + " " + (char) i);
		}
	}

}
