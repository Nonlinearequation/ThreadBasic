package main.threadPool;

import main.util.SleepHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.*;

public class T07_CachedThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int j = i;
            executor.submit(()->{
                try {
                    TimeUnit.MICROSECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(j + Thread.currentThread().getName());
            });
        }

        System.out.println(executor);
        TimeUnit.SECONDS.sleep(10);
        System.out.println(executor);
        executor.shutdown();

    }
}
