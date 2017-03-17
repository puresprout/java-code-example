package search;

/**
 * Created by 1002703 on 2017. 1. 24..
 */
public class BinarySearch {
    public int search(int[] values, int x) {
        int low = 0;
        int high = values.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (values[mid] > x) {
                high = mid - 1;
            } else if (values[mid] < x) {
                low = mid + 1;
            } else if (values[mid] == x) {
                return mid;
            }
        }

        return -1;
    }
}
