/*
 * Properties.java
 *
 * Created on 2003년 12월 17일 (수), 오후 1:7
 */

package properties;

import java.io.*;
import java.util.*;

/**
 *
 * @author  박성현
 */
public class Property {
    
    /** Creates a new instance of Properties */
    public Property() {
    }
    
    public static void main(String args[]) {
        FileOutputStream fout = null;
        
        Properties properties = new Properties();
        
        properties.setProperty("ID", "박성현");
        properties.setProperty("Password", "fjgh56");
        
        try {
            fout = new FileOutputStream("properties/conf/Config.properties");
            properties.store(fout, "Config 파일");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
