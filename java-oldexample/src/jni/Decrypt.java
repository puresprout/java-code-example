package jni;

import java.io.*;

public class Decrypt {
	private boolean debug = true;
	public native int RSAInit(String keyName);
	public native int DecryptData(byte[] src, int offset, byte[] dest, int n);
	
	public static void main(String args[]) {
		Decrypt d = new Decrypt();
		d.process(args);
	}

	public void process(String args[]) {
		if (args.length != 2) {
			System.out.println("Usage: Decrypt srcFile destFile");
			return;
		}

		File srcFile = new File(args[0]);
		if (!srcFile.isFile()) {
			System.out.println(args[0] + "is not file or not exist");
			return;							                }
		
		RSAInit("secret.key");

		int fileSize = (int) srcFile.length();
		byte[] src = new byte[fileSize];

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(srcFile);
			fis.read(src, 0, fileSize);
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		byte[] dest = new byte[fileSize];
		int n = DecryptData(src, fileSize, dest, fileSize);
		if (debug)
			System.out.println("nRet : " + n);

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(args[1]);
			fos.write(dest, 0, n);
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	static {
		System.loadLibrary("rsanseed");
	}
}

