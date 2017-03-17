package sort;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class QuickSortTest {
    @Test
    public void quickSort() {
        int[] array = {5, 9, 10, 12, 3, 1, 99, 45};
        QuickSort.sort(array);

        int[] expected = {1, 3, 5, 9, 10, 12, 45, 99};
        assertThat(array, is(expected));
    }

    @Test
    public void quickSort2() {
        int[] array = {100, 23, 12, 5, 9, 10, 12, 3, 1, 99, 45};
        QuickSort.sort(array);

        assertThat(array, isInAscendingOrder());
    }

    private Matcher<int[]> isInAscendingOrder() {
        return new TypeSafeMatcher<int[]>() {
            @Override
            public void describeTo(Description description) {
            }

            @Override
            protected boolean matchesSafely(int[] items) {
                for (int i = 0; i < items.length - 1; i++) {
                    if (items[i] > items[i + 1]) {
                        return false;
                    }
                }

                return true;
            }
        };
    }
}
