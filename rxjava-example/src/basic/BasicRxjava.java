package basic;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sunghyun on 2017. 3. 17..
 */
public class BasicRxjava {
    public static void main(String[] args) throws InterruptedException {
        Flowable.fromCallable(() -> {
            System.out.println("Computating...");
            Thread.sleep(3000);
            return "Done";
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(System.out::println, Throwable::printStackTrace);

        Thread.sleep(4000);
    }
}
