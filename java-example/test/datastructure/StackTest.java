package datastructure;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by 1002703 on 2017. 1. 24..
 */
public class StackTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void stack() {
        ArrayStack<String> stack = new ArrayStack<>(5);

        stack.push("abc");
        stack.push("def");
        stack.push("ghi");

        assertThat(stack.pop(), is("ghi"));
        assertThat(stack.pop(), is("def"));
        assertThat(stack.pop(), is("abc"));

        exception.expect(ArrayIndexOutOfBoundsException.class);
        stack.pop();

        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        stack.push("5");

        exception.expect(ArrayIndexOutOfBoundsException.class);
        stack.push("6");
    }
}
