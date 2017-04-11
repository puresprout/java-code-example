/*
 * HelloI18N.java
 *
 * Created on 2003�� 12�� 17�� (��), ���� 9:14
 */
package internationalization;

import java.util.*;

public class HelloI18N {
    
    /** Creates a new instance of HelloI18N */
    public HelloI18N() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle("Internationalization.MBroker");
        
        System.out.println(rb.getString("TCPIPAdapter"));
        System.out.println(rb.getString("SystemAdapter"));
    }
}
