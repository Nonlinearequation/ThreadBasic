package main.sychronized;

/**
 * synchronized方法
 */
public class C01_Tmethod {

    private int count = 10;

    public synchronized void m(){
        count--;
        System.out.println(Thread.currentThread().getName()+"count = "+count);
    }

}
