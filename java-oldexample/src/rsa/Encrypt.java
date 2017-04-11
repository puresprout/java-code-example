package rsa;

import java.io.*;

public class Encrypt {
	private boolean debug = true;
	public native int RSAInit(String keyName);
	public native int EncryptData(byte[] src, int offset, byte[] dest, int n);

	public static void main(String args[])
	{
		Encrypt e = new Encrypt();
		e.process(args);
	}

	public void process(String args[]) {
		if (args.length != 2) {
			System.out.println("Usage: Encrypt srcFile destFile");
			return;
		}

		File srcFile = new File(args[0]);
		if (!srcFile.isFile()) {
			System.out.println(args[0] + "is not file or not exist.");
			return;
		}

		RSAInit("public.key");
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
		
		int n = (((fileSize)/16)+1)*16;
		n += 4;
		n *= 2;
		n += 66;
		n++;
		
		byte[] dest = new byte[n];
		int nRet = EncryptData(src, fileSize, dest, n);
		if (debug)
			System.out.println("nRet : " + nRet);

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
		System.loadLibrary("EncryptDecrypt");
	}
}
