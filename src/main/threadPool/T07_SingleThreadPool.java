package main.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

public class T07_SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService service = newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            final int j = i;
            service.submit(()->{
                System.out.println(j + Thread.currentThread().getName());
            });
        }

    }
}
