package main.sychronized;

public class C01_synchroized对象 implements Runnable{

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new C01_synchroized对象()).start();
        }
    }

    private static int count = 10;
    private static Object o = new Object();

    public static void m(){
        synchronized(o){
            count--;
            System.out.println(Thread.currentThread().getName()+" count="+count);
        }
    }

    public void m2(){
        synchronized(this){
            count--;
            
        }
    }


    @Override
    public void run() {
        synchronized(o){
            count--;
            System.out.println(Thread.currentThread().getName()+" count="+count);
        }
    }
}
