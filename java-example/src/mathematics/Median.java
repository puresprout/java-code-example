package mathematics;

/**
 * Created by sunghyun on 2016. 12. 1..
 */
public class Median {
    public static int getMedian(int[] val) {
        int size = 0;
        for (int i = 0; i < val.length; i++) {
            if (val[i] > 0) {
                size += val[i];
            }
        }

        int midPos;
        if (size % 2 == 1) {
            midPos = (size + 1) / 2;
        } else {
            midPos = size / 2;
        }

        int sum = 0;
        for (int i = 0; i < val.length; i++) {
            sum += val[i];
            if (sum >= midPos) {
                return i;
            }
        }

        return -1;
    }
}
