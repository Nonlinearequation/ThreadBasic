package main.threadPool;

import java.util.concurrent.*;

public class T06_FutureTask {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(()->{
            TimeUnit.SECONDS.sleep(1);
            return "ok";
        });
//        new Thread(futureTask).start();//thread和线程池都可以
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(futureTask);
        System.out.println(futureTask.get());//阻塞
        executorService.shutdown();
    }
}
