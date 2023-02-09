package main;

import util.SleepHelper;

/**
 * 线程设置标志位interrupt 和是否设置线程标志位isInterrupted
 */
public class T05_Interrupt_2 {

    public static void main(String[] args) {
        // 中断线程，线程不满足条件所以退出while循环
        Thread t = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                /*
                 可以用于线程中业务处理，监测到中断信号，就停止做某件事，但保证代码块内的方法是可以执行完的
                 缺点：这样就要把线程代码 都写在while代码块内
                 */
                System.out.println("not interrupted");
            }
        });
        System.out.println("start");
        t.start();
        SleepHelper.sleepSeconds(1);
        t.interrupt();

        // 当线程处于阻塞状态时，中断线程会抛出一个InterruptedException异常
        Thread t2 = new Thread(() -> {
            try {
                System.out.println("进入SleepThread线程");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();
        t2.interrupt();
        // 要处理的话，可以catch异常，手动处理

    }

}
