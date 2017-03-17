package chars;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by sunghyun on 2017. 1. 14..
 */
public class CharUtilTest {
    @Test
    public void reverse() throws Exception {
        char[] source = "abc".toCharArray();
        CharUtil.reverse(source);
        assertThat(source, is("cba".toCharArray()));
    }

    @Test
    public void reverse2() throws Exception {
        char[] source = "software korea".toCharArray();
        CharUtil.reverse(source);
        assertThat(source, is("aerok erawtfos".toCharArray()));
    }

    @Test
    public void reverse3() throws Exception {
        char[] source = "".toCharArray();
        CharUtil.reverse(source);
        assertThat(source, is("".toCharArray()));
    }

    @Test
    public void reverse4() throws Exception {
        char[] source = "g".toCharArray();
        CharUtil.reverse(source);
        assertThat(source, is("g".toCharArray()));
    }

    @Test
    public void reverse5() throws Exception {
        char[] source = null;
        CharUtil.reverse(null);
        assertThat(source, is(nullValue()));
    }
}