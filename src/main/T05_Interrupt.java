package main;

import main.util.SleepHelper;

/**
 * 线程设置标志位interrupt 和是否设置线程标志位isInterrupted
 */
public class T05_Interrupt {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            for(;;){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("t is interrupted!");
                    System.out.println(Thread.currentThread().isInterrupted());
                }
            }
        });

        t.start();
        SleepHelper.sleepSeconds(2);
        t.interrupt();
    }

}
