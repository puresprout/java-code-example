package hello;

import io.reactivex.Flowable;

/**
 * Created by sunghyun on 2017. 3. 16..
 */
public class HelloWorld {
    public static void main(String[] args) {
        Flowable.just("Hello rxjava").subscribe(System.out::println);
    }
}
