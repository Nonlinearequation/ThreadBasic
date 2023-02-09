package main;

import util.SleepHelper;

/**
 * 线程设置标志位interrupt 和是否设置线程标志位isInterrupted
 * interrupt是通知被停止线程，仅仅是打个标记。需要代码处理中断线程后要做的事
 */
public class T05_Interrupt {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            for(;;){
                if(!Thread.currentThread().isInterrupted()){
                    System.out.println("t is not interrupted!");
                    System.out.println(Thread.currentThread().isInterrupted());
                }else {
                    // 接收到线程中断信号，手动停止线程
                    System.out.println("t is interrupted");
                    Thread.currentThread().stop();
                }
            }
        });

        t.start();
        SleepHelper.sleepSeconds(1);
        // 主线程获取cpu资源，中断线程t
        t.interrupt();
        System.out.println("end!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

}
