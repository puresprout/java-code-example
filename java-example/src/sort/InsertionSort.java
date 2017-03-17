package sort;

/**
 * Created by sunghyun on 2017. 2. 5..
 */
public class InsertionSort {
    public static void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int value = array[i];

            int j;
            for (j = i - 1; j >= 0 && value < array[j]; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = value;
        }
    }

    public static <T extends Comparable<T>> void sort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            T value = array[i];

            int j;
            for (j = i - 1; j >= 0 && value.compareTo(array[j]) < 0; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = value;
        }
    }
}
