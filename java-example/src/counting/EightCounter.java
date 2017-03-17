package counting;

/**
 * Created by sunghyun on 2016. 12. 20..
 */
public class EightCounter {
    public int count(int value) {
        int sum = 0;

        while (value > 0) {
            if (value % 10 == 8) {
                sum++;
            }
            value /= 10;
        }

        return sum;
    }

    public int countRange(int startValue, int endValue) {
        if (endValue < startValue) {
            throw new IllegalArgumentException("endValue는 startValue와 같거나 커야한다.");
        }

        int sum = 0;

        for (int i = startValue; i <= endValue; i++) {
            sum += count(i);
        }

        return sum;
    }
}
