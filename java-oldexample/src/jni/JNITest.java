package jni;

public class JNITest {
    public JNITest() {
    }
    
    public native long ToDouble(int value);
    public native String Concat(String str[]);
    
    public static void main(String args[])
    {
        JNITest test = new JNITest();
        
	System.out.println(test.Concat(args));
	
        /*for (int i = 1; i <= 10; i++)
        {
            System.out.println(test.ToDouble(i));
        }*/
    }

    static {
        System.loadLibrary("Native");
    }
}
