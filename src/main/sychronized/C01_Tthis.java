package main.sychronized;

/**
 * synchronized(this)
 */
public class C01_Tthis {

    private int count = 10;

    public void m(){
        synchronized(this){
            count--;
            System.out.println(Thread.currentThread().getName() + "count = "+count);
        }
    }

}
