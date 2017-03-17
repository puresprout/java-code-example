package compression;

/**
 * Created by sunghyun on 2017. 1. 16..
 */
public class StringCompression {
    public String compress(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        StringBuilder sb = new StringBuilder();

        char c = s.charAt(0);
        int count = 1;

        for (int i = 1; i < s.length(); i++) {
            if (c == s.charAt(i)) {
                count++;
            } else {
                sb.append(c);
                sb.append(count);

                c = s.charAt(i);
                count = 1;
            }
        }

        sb.append(c);
        sb.append(count);

        return sb.toString();
    }
}
