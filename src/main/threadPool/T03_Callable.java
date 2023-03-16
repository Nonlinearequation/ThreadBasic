package main.threadPool;

import java.util.concurrent.*;

import static java.util.concurrent.Executors.*;

public class T03_Callable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> call = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello";
            }
        };

        ExecutorService service = newCachedThreadPool();
        Future<String> future = service.submit(call);//异步

        System.out.println(future.get());//阻塞

        service.shutdown();

    }

}
