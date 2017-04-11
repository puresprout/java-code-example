/*
 * Log.java
 *
 * Created on 2003년 12월 17일 (수), 오후 5:28
 */

package logging;

import java.util.logging.*;
import java.io.*;
/**
 *
 * @author  박성현
 */
public class Log {
    private static Logger logger = Logger.getLogger("Logging.Log");  // 로그 기능
    
    /** Creates a new instance of Log */
    public Log() {
    }
    
    public static void main(String args[]) {
        try {
            logger.setUseParentHandlers(false);
            FileHandler fh = new FileHandler("Logging\\Log.log");
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        logger.setLevel(Level.ALL);

        logger.log(Level.INFO, "로그가 생성되었나?");
    }
    
}
