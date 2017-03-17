package sort;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by sunghyun on 2017. 2. 5..
 */
public class InsertionSortTest {
    @Test
    public void sort() {
        int[] array = {100, 30, 5, 99, 39, 12, 84};
        InsertionSort.sort(array);

        int[] expected = {5, 12, 30, 39, 84, 99, 100};
        assertThat(array, is(expected));
    }

    @Test
    public void sort2() {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100);
        }

        for (int i : array) {
            System.out.print(array[i] + " ");
        }

        InsertionSort.sort(array);

        System.out.println();

        for (int i : array) {
            System.out.print(array[i] + " ");
        }

        assertThat(array, isInAscendingOrder());
    }


    @Test
    public void sortString() {
        String[] strs = {"a", "f", "g", "z", "f", "b"};
        InsertionSort.sort(strs);

        String[] expected = {"a", "b", "f", "f", "g", "z"};
        assertThat(strs, is(expected));
    }

    private Matcher<int[]> isInAscendingOrder() {
        return new TypeSafeMatcher<int[]>() {
            @Override
            protected boolean matchesSafely(int[] ints) {
                for (int i = 0; i < ints.length - 1; i++) {
                    if (ints[i] > ints[i + 1]) {
                        return false;
                    }
                }

                return true;
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }
}
