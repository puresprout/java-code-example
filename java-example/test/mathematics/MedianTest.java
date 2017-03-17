package mathematics;

import org.junit.Test;

/**
 * Created by sunghyun on 2017. 1. 12..
 */
public class MedianTest {
    @Test
    public void getMedian() throws Exception {
        int[] val = new int[10];

        val[5] = 4;
        val[9] = 1;

        int result = Median.getMedian(val);

        System.out.println(result);
    }

    @Test
    public void getMedian2() {
        int[] val = new int[10];

        val[1] = 3;
        val[2] = 1;
        val[3] = 1;
        val[9] = 2;

        int result = Median.getMedian(val);

        System.out.println(result);
    }
}