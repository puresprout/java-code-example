package search;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by 1002703 on 2017. 1. 24..
 */
public class BinarySerachTest {
    BinarySearch search;

    @Before
    public void setUp() {
        search = new BinarySearch();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void binarySearch() {
        int[] values = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        assertThat(search.search(values, 5), is(4));
        assertThat(search.search(values, 1), is(0));
        assertThat(search.search(values, 9), is(8));
    }

    @Test
    public void binarySearch2() {
        int[] values = new int[]{5, 10, 13, 45, 100, 145, 1001};

        assertThat(search.search(values, 12), is(-1));
        assertThat(search.search(values, 5), is(0));
        assertThat(search.search(values, 45), is(3));
    }
}
