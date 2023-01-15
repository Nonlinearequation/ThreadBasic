package main;

import util.SleepHelper;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class T04_ThreadState {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(">===new和runnable状态========================================================");
        Thread t1 = new Thread(()->{
            System.out.println("2:"+Thread.currentThread().getState());//第二步：开始执行线程，状态为runnable
            for (int i = 0; i < 3; i++) {
                SleepHelper.sleepSeconds(1);
                System.out.print(i+" ");
            }
            System.out.println();
        });
        System.out.println("1: "+t1.getState());//第一步：先启动，线程状态为new
        t1.start();
        t1.join();//等待线程执行结束
        System.out.println("3: "+t1.getState());//第三步：线程结束 terminated


        System.out.println(">===wating和timedWaiting状态========================================================");
        Thread t2 = new Thread(()->{
           try{
               LockSupport.park();//2.t2执行，线程阻塞
               System.out.println("t2,go on!");
               TimeUnit.SECONDS.sleep(3);//4.t2进入等待3秒，这时状态就是timedWating
           }catch (Exception e){
               e.printStackTrace();
           }
        });
        t2.start();//1.启动t2线程，然后让主线程sleep 1秒，保证t2执行起来
        TimeUnit.SECONDS.sleep(1);
        System.out.println("4: "+t2.getState());//第四步：由1 2可知，t2阻塞后进入wating状态，等待被唤醒

        LockSupport.unpark(t2);//3.唤醒t2，下一行代码让主线程等待1秒，保证t2被唤醒继续执行
        TimeUnit.SECONDS.sleep(1);
        System.out.println("5: "+t2.getState());//第五步：由3 4可知，t2的状态是timedWating

        System.out.println(">===block演示========================================================");
        final Object o = new Object();//锁
        Thread t3 = new Thread(()->{
           synchronized (o){
               System.out.println("t3 得到了锁");
           }
        });

        new Thread(()->{
            synchronized(o){
                SleepHelper.sleepSeconds(5);
            }
        }).start();//1.启动线程，下面让主线程休息1秒是为了让此线程获取锁
        SleepHelper.sleepSeconds(1);

        t3.start();//2.启动t3，下面让主线程休息1秒也是为了让t3去获取锁，但此时锁在上一个线程里，所以t3的状态为block
        SleepHelper.sleepSeconds(1);
        System.out.println("6: "+t3.getState());//第六步：由1 2可知，t3的状态为block

        System.out.println(">===lock和synchronized线程状态的区别========================================================");
        final Lock lock = new ReentrantLock();
        Thread t4 = new Thread(()->{
           lock.lock();
           System.out.println("t4 得到了锁");
           lock.unlock();
        });

        new Thread(()->{
            lock.lock();
            SleepHelper.sleepSeconds(5);
            lock.unlock();
        }).start();//1.此线程先启动，获取到lock锁，ReentrantLock锁是cas实现，下面的主线程休眠1秒是为了让此线程获取到锁
        SleepHelper.sleepSeconds(1);

        t4.start();//2.t4启动，下面的主线程休眠1秒同样是为了让t4的第一行代码执行，去获取锁，
        // 但此时锁还在上面的线程中，所以状态是waiting
        SleepHelper.sleepSeconds(1);
        System.out.println("7: "+t4.getState());//由1 2可知，t4的状态时waiting

        System.out.println(">===park之后的线程状态========================================================");
        Thread t5 = new Thread(()->{
            LockSupport.park();
        });
        t5.start();//1.启动t5，下一行让主线程休眠1秒是为了执行park
        SleepHelper.sleepSeconds(1);
        System.out.println("8: "+t5.getState());//第八步：获取状态park
        LockSupport.unpark(t5);//解除park




    }

}
