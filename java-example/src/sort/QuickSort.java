package sort;

public class QuickSort {
    public static void sort(int[] array) {
        sortInternal(array, 0, array.length - 1);
    }

    private static void sortInternal(int[] array, int i, int j) {
        int left = i;
        int right = j;
        int pivot = array[(i + j) / 2];

        do {
            while (array[left] < pivot) {
                left++;
            }

            while (array[right] > pivot) {
                right--;
            }

            if (left <= right) {
                swap(array, left, right);
                left++;
                right--;
            }
        } while (left <= right);

        if (i < right) {
            sortInternal(array, i, right);
        }

        if (j > left) {
            sortInternal(array, left, j);
        }
    }

    private static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
