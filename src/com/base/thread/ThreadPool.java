package com.base.thread;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
	public static void main(String[] args) {


        ThreadPool pool=new ThreadPool();

        pool.test();


    }

    private void test() {

//newSingleThreadExecutor 生成单线程的线程池
//        ExecutorService service = Executors.newSingleThreadExecutor();
//        service.execute(new SayHi());
//        service.execute(new SayHi());
//        service.shutdown();
//newFixedThreadPool 生成固定线程数的线程池
//        ExecutorService service = Executors.newFixedThreadPool(2);
//        service.execute(new SayHi());
//        service.execute(new SayHi());
//        service.execute(new SayHi());
//        service.shutdown();

        //newCachedThreadPool 生成不固定线程数的线程池
//                ExecutorService service = Executors.newCachedThreadPool();
//        service.execute(new SayHi());
//        service.execute(new SayHi());
//        service.execute(new SayHi());
//        service.shutdown();


        ScheduledExecutorService schedul = Executors.newScheduledThreadPool(2);
        //只是延迟执行
        //schedul.schedule(new SayHi(),1, TimeUnit.SECONDS);

        //以固定延迟时间 按照上次job的完成时间计算+2
        //schedul.scheduleWithFixedDelay(new SayHi(),1,2,TimeUnit.SECONDS);

        //以固定延迟间隔 按照上次job的开始时间计算+2 如果执行间隔短于job执行时间则按照job执行时间计算。
        schedul.scheduleAtFixedRate(new SayHi(),1,4,TimeUnit.SECONDS);
        System.out.println(new Date());


    }


    class  SayHi implements Runnable{


        @Override
        public void run() {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("th_id:%s;th_name:%s;%s"
                    ,Thread.currentThread().getId()
                    ,Thread.currentThread().getName()
            ,new Date()));

        }
    }
}
