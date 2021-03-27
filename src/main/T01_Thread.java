package main;

import java.util.concurrent.*;

import static java.util.concurrent.Executors.*;

/**
 * 实现多线程的方法1：继承Thread类
 *            方法2：实现Runnable接口
 *            方法3：实现Callable接口
 *            方法4：使用线程池ExecutorService创建线程
 */
public class T01_Thread {

    //方法1：继承Thread类
    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("Hello,Thread");
        }
    }

    //方法2：实现Runnable接口
    static class MyRun implements Runnable{
        @Override
        public void run() {
            System.out.println("Hello,Run");
        }
    }

    //方法3：实现Callable接口，和runnable的区别：可以有返回值
    static class MyCall implements Callable<String>{
        @Override
        public String call() throws Exception {
            System.out.println("Hello,MyCall");
            return "success";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //直接新建一个继承thread类的类的对象
        new MyThread().start();
        //新建一个thread，并把实现callable接口的类传进去
        new Thread(new MyRun()).start();
        //lambda表达式实现，实际应该是1的变种
        new Thread(()-> System.out.println("Hello,Lambda")).start();

        //方法4：使用线程池ExecutorService创建线程
        ExecutorService service = newCachedThreadPool();
        //lambda表达式
        service.execute(()->{
            System.out.println("Hello,ExecutorService");
        });
        //runnable接口
        System.out.println("executor执行runnable对象的结果：↓");//打印位置不在hello,run上面是正常的
        service.submit(new MyRun());

        //方法5：Future Callable和FutureTask
        //callable接口，有返回值
        Future<String> future = service.submit(new MyCall());
        String res = future.get();
        System.out.println("Future执行callable对象的结果："+res);
        service.shutdown();

        //不用线程池，拿到多线程方法的返回值。Thread不能直接传Callable，也就不能接收多线程返回结果
        FutureTask<String> task = new FutureTask<>(new MyCall());
        Thread t = new Thread(task);
        t.start();
        System.out.println("FutureTask拿到返回结果："+task.get());
    }

}
