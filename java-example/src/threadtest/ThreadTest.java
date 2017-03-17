package threadtest;

/**
 * Created by sunghyun on 2016. 12. 2..
 */
public class ThreadTest {
    public static void main(String args[]) {
        Common common = new Common();

        MyThread1 t1 = new MyThread1();
        t1.setCommon(common);
        t1.start();

        MyThread2 t2 = new MyThread2();
        t2.setCommon(common);
        t2.start();
    }

    static class Common {
        String name = "default";

        public synchronized void setName(String name) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.name = name;
        }

        // NOTICE 이 메소드에 synchronized 키워드를 넣으면, 타쓰레드가 setName() 호출중에는 현쓰레드가 이 메소드에도 들어올 수 없다.
        public String getName() {
            return name;
        }
    }

    static class MyThread1 extends Thread {
        Common common;

        public void setCommon(Common common) {
            this.common = common;
        }

        @Override
        public void run() {
            common.setName("thread 1 name");

            System.out.println("thread1 end");
        }
    }

    static class MyThread2 extends Thread {
        Common common;

        public void setCommon(Common common) {
            this.common = common;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(common.getName());

            System.out.println("thread2 end");
        }
    }
}
