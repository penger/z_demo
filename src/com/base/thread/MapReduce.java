package com.base.thread;

import java.util.Random;

public class MapReduce {
	public static void main(String[] args) {

        //矩阵10行10000列的矩阵
        int [][]  bigMatrix=new int[20][10000];
        for(int i=0;i<bigMatrix.length;i++){
            for (int j=0;j<bigMatrix[i].length;j++){
                bigMatrix[i][j]= new Random().nextInt();
                System.out.println(bigMatrix[i][j]);
            }
        }
        int result=Integer.MIN_VALUE;
        Worker[] worker=new Worker[bigMatrix.length];
        Thread[] threads=new Thread[bigMatrix.length];
        //map
        for (int i=0;i<bigMatrix.length;i++){
            worker[i]=new Worker(bigMatrix[i]);
            threads[i]=new Thread(worker[i]);
            threads[i].start();
        }
        //reduce
        for (int i=0;i<bigMatrix.length;i++){
            try {
                threads[i].join();
                result=Math.max(result,worker[i].getMax());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //mapreduce结果
        System.out.println(result);
        int result2=Integer.MIN_VALUE;
        for(int i=0;i<bigMatrix.length;i++){
            for (int j=0;j<bigMatrix[i].length;j++){
                result2=Math.max(result2,bigMatrix[i][j]);
            }
        }
        //正常的结果
        System.out.println(result2);
    }


    static class Worker implements Runnable{

        private int max=Integer.MIN_VALUE;
        private int[] array;

        public Worker(int[] array) {
            this.array = array;
        }

        public void run() {
            for(int i=0;i<array.length;i++){
                max=Math.max(max,array[i]);
            }
        }
        public int getMax(){
            return max;
        }
    }
}
