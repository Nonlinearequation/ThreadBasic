package main.sychronized;

/**
 * 能不能同时调用同步方法和非同步方法？
 * 根据运行结果看，可以
 */
public class C07_同步方法 {

    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+" m1 start");
        try{
            Thread.sleep(10000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m1 end");
    }

    public void m2(){
        System.out.println(Thread.currentThread().getName()+" m2 start");
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m2 end");
    }

    public static void main(String[] args) {
        C07_同步方法 c = new C07_同步方法();
        new Thread(c::m1).start();
        new Thread(c::m2).start();
    }

}
