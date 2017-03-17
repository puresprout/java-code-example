package compression;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by sunghyun on 2017. 1. 16..
 */
public class StringCompressionTest {
    private StringCompression sc;

    @Before
    public void setUp() {
        sc = new StringCompression();
    }

    @After
    public void tearDown() {
        sc = null;
    }

    @Test
    public void compress() {
        assertThat(sc.compress("aabccccccccaaa"), is("a2b1c8a3"));

        assertThat(sc.compress(""), is(""));

        assertThat(sc.compress(null), is(nullValue()));

        assertThat(sc.compress("abc"), is("a1b1c1"));

        assertThat(sc.compress("z"), is("z1"));
    }
}