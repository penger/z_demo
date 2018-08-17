package com.base.thread;

public class ReadMe {

    public static void main(String[] args) throws InterruptedException {

        ReadMe readMe = new ReadMe();
        readMe.test();

    }

    private void test() throws InterruptedException {


        Thread thread=new Thread(new SayHi(),"th");
        thread.start();

        Thread.sleep(1000);

        thread.interrupt();
    }
    class SayHi implements  Runnable{


        @Override
        public void run() {


            while (true){

                if (Thread.interrupted()){

                    break;
                }
                ThreadLocal<String> local=new ThreadLocal<String>();

                local.set("ssss");



                System.out.println(local.get());
            }
        }
    }
}
