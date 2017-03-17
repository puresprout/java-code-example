package recursion;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by sunghyun on 2016. 12. 21..
 */
public class ParenthesisTest {
    @Test
    public void generateParen() {
        Set<String> set = Parenthesis.generateParen(1);
        System.out.println(set);

        String[] expected = {"()"};
        Set<String> expectedSet = new HashSet<>(Arrays.asList(expected));
        assertThat(set, is(expectedSet));
    }

    @Test
    public void generateParen2() {
        Set<String> set = Parenthesis.generateParen(2);
        System.out.println(set);

        String[] expected = {"()()", "(())"};
        Set<String> expectedSet = new HashSet<>(Arrays.asList(expected));
        assertThat(set, is(expectedSet));
    }

    @Test
    public void generateParen3() {
        Set<String> set = Parenthesis.generateParen(3);
        System.out.println(set);

        String[] expected = {"()()()", "((()))", "()(())", "(())()", "(()())"};
        Set<String> expectedSet = new HashSet<>(Arrays.asList(expected));
        assertThat(set, is(expectedSet));
    }
}