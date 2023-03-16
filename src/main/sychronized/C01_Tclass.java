package main.sychronized;

public class C01_Tclass {
    private static int count = 10;

    public synchronized static void m(){
        count--;
        System.out.println(Thread.currentThread().getName()+"count = "+count);
    }

    public static void mm(){
        synchronized (C01_Tclass.class){
            count--;
        }
    }

}
