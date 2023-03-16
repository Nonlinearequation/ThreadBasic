package main.volatile关键字;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.atomic.AtomicInteger;

public class C01_对象布局 {

    public static void main(String[] args) {

        AtomicInteger a = new AtomicInteger();
        a.incrementAndGet();


        //查看对象布局
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o){
            System.out.println(ClassLayout.parseInstance(0).toPrintable());
        }
    }

}
