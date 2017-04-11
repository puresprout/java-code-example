/*
 * RandomAccessFile.java
 *
 * Created on 2004년 2월 3일 (화), 오후 4:59
 */

package inputOutput;

/**
 *
 * @author  박성현
 */
public class RandomAccessFile {
    
    /** Creates a new instance of RandomAccessFile */
    public RandomAccessFile() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws java.io.FileNotFoundException, java.io.IOException {
        java.io.RandomAccessFile raf = new java.io.RandomAccessFile("raf.txt", "rw");
        
        // 파일에 0부터 9까지 기록
        for (int i = 0; i < 10; i++)
            raf.write(i);
        
        // 파일에서 9부터 0까지 거꾸로 읽어 출력한다.
        for (int i = 9; i >= 0; i--) {
            raf.seek(i);
            System.out.println(raf.read());
        }
        
        raf.close();
    }
    
}
