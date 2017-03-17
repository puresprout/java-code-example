package csv;

import org.junit.Test;

/**
 * Created by sunghyun on 2017. 2. 5..
 */
public class SplitTest {
    @Test
    public void split() {
        String s = "abc";

        String[] ss = s.split(",");
        for (String str : ss) {
            System.out.println(str);
        }
    }
}
