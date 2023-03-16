package main.threadPool;

import main.util.SleepHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class T10_TPETest {

    public volatile static int endNum = -1;

    private static class Task implements Runnable{
        private int i;

        public Task(int i){
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Task" + i);
            while(endNum != 1){//不结束的任务
                SleepHelper.sleepSeconds(100);
            }
            System.out.println("Task " + i +" 结束!");
        }

        @Override
        public String toString() {
            return "Task{" +
                    "i=" + i +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2,4,
                60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(4),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        Task t1 = new Task(1);
        tpe.execute(t1);
        //tpe.submit(t1);//也可以执行，区别在submit异步有返回值，execute同步无返回值
        System.out.println(tpe.getPoolSize());
        System.out.println(tpe.getQueue());

        System.out.println("----------------------------------");

        Task t2 = new Task(2);
        tpe.execute(t2);
        System.out.println(tpe.getPoolSize());
        System.out.println(tpe.getQueue());

        System.out.println("----------------------------------");

        Task t3 = new Task(3);
        tpe.execute(t3);
        Task t4 = new Task(4);
        tpe.execute(t4);
        Task t5 = new Task(5);
        tpe.execute(t5);
        System.out.println(tpe.getPoolSize());//线程为2
        System.out.println(tpe.getQueue());

        System.out.println("----------------------------------");

        Task t6 = new Task(6);
        tpe.execute(t6);
        System.out.println(tpe.getPoolSize());//线程仍为2，没有增加线程
        System.out.println(tpe.getQueue());

        System.out.println("----------------------------------");

        Task t7 = new Task(7);
        tpe.execute(t7);
        System.out.println(tpe.getPoolSize());//线程为3，增加了一个线程
        System.out.println(tpe.getQueue());

        System.out.println("----------------------------------");

        Task t8 = new Task(8);
        tpe.execute(t8);
        System.out.println(tpe.getPoolSize());//线程为4，增加一个线程
        System.out.println(tpe.getQueue());

        System.out.println("----------------------------------");

        Task t9 = new Task(9);
        tpe.execute(t9);
        System.out.println(tpe.getPoolSize());//线程为4，增加一个线程
        System.out.println(tpe.getQueue());

        System.out.println("----------------------------------");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(;;){
            endNum = Integer.parseInt(br.readLine().trim());
            SleepHelper.sleepSeconds(1);
            System.out.println(tpe);
            System.out.println(tpe.getQueue());
        }

    }

}
