package recursion;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sunghyun on 2016. 12. 21..
 */
public class Parenthesis {
    public static Set<String> generateParen(int count) {
        Set<String> set = new HashSet<>();

        if (count == 0) {
            // nothing to do
        } else {
            Set<String> parenSet = generateParen(count - 1);
            if (parenSet.isEmpty()) {
                set.add("()");
            } else {
                for (String str : parenSet) {
                    for (int i = 0; i < str.length(); i++) {
                        if (str.charAt(i) == ')') {
                            set.add(insertInside(str, i));
                        }
                    }
                    set.add(str + "()");
                }
            }
        }

        return set;
    }

    private static String insertInside(String str, int i) {
        String left = str.substring(0, i);
        String right = str.substring(i, str.length());
        return left + "()" + right;
    }
}
