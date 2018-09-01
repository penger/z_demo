package com.base.designpattern.play.football;

/**
 * Created by gongp on 2018/8/24.
 */
public class Test {
    public static void main(String[] args) {
//        testfactory();
//        testAbstractFactory();


        try{
            int k = 0/2;
            System.out.println("k");
            return;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("finally ");
        }

    }

    private static void testAbstractFactory() {
        BallFactory af = new AdidasFactory();
        af.createBall();
    }

    private static void testfactory() {
    }

}
