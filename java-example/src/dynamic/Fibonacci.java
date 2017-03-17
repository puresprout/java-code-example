package dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunghyun on 2016. 12. 22..
 */
public class Fibonacci {
    public static List<Integer> getFibonacci(int n) {
        List<Integer> list = new ArrayList<>();

        if (n < 0) {
            throw new IllegalArgumentException("n should be equal or greater than 0.");
        } else if (n == 0) {
            return list;
        } else {
            for (int i = 1; i <= n; i++) {
                if (i == 1) {
                    list.add(0);
                } else if (i == 2) {
                    list.add(1);
                } else {
                    list.add(list.get(list.size() - 2) + list.get(list.size() - 1));
                }
            }

            return list;
        }
    }
}
