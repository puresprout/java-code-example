package chars;

/**
 * Created by sunghyun on 2017. 1. 14..
 */
public class CharUtil {
    public static void reverse(char[] chars) {
        if (chars == null) {
            return;
        }

        int length = chars.length;
        int mid = length / 2;
        for (int i = 0; i < mid; i++) {
            int j = length - i - 1;
            char c = chars[i];
            chars[i] = chars[j];
            chars[j] = c;
        }
    }
}
