package dynamic;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertThat;

/**
 * Created by sunghyun on 2016. 12. 22..
 */
public class FibonacciTest {
    @Test(expected = RuntimeException.class)
    public void fibonacci() throws Exception {
        assertThat(Fibonacci.getFibonacci(-1), CoreMatchers.is(Arrays.asList()));
    }

    @Test
    public void fibonacci2() throws Exception {
        assertThat(Fibonacci.getFibonacci(0), CoreMatchers.is(Arrays.asList()));
        assertThat(Fibonacci.getFibonacci(1), CoreMatchers.is(Arrays.asList(0)));
        assertThat(Fibonacci.getFibonacci(2), CoreMatchers.is(Arrays.asList(0, 1)));
        assertThat(Fibonacci.getFibonacci(3), CoreMatchers.is(Arrays.asList(0, 1, 1)));
        assertThat(Fibonacci.getFibonacci(4), CoreMatchers.is(Arrays.asList(0, 1, 1, 2)));
    }
}