package main.volatile关键字;

import java.util.concurrent.TimeUnit;

//可见性，如果没有volatile线程不会结束
public class C02_volatile {
    volatile boolean running = true;//对比有无volatile的结果
    void m(){
        System.out.println("m start");
        while(running){
        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        C02_volatile t = new C02_volatile();
        new Thread(t::m,"t1").start();

        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        t.running = false;
    }
}
