package counting;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by sunghyun on 2016. 12. 20..
 */
public class EightCounterTest {
    @Test
    public void count() {
        EightCounter counter = new EightCounter();
        assertThat(counter.count(0), is(0));
        assertThat(counter.count(1), is(0));
        assertThat(counter.count(9), is(0));
        assertThat(counter.count(10), is(0));
        assertThat(counter.count(11), is(0));
        assertThat(counter.count(8), is(1));
        assertThat(counter.count(80), is(1));
        assertThat(counter.count(88), is(2));
        assertThat(counter.count(800), is(1));
        assertThat(counter.count(880), is(2));
        assertThat(counter.count(808), is(2));
        assertThat(counter.count(888), is(3));
    }

    @Test
    public void countRange() throws Exception {
        EightCounter counter = new EightCounter();
        System.out.println(counter.countRange(1, 10000));

        assertThat(counter.countRange(1, 10), is(1));
        assertThat(counter.countRange(1, 20), is(2));
        assertThat(counter.countRange(1, 30), is(3));
        assertThat(counter.countRange(1, 100), is(20));
    }
}