package com.study.implement.design.Concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueProducerConsumer {

   public static class Producer implements Runnable{

       private final BlockingQueue blockingQueue;

       public Producer(BlockingQueue blockingQueue) {
           this.blockingQueue = blockingQueue;
       }

       @Override
       public void run() {
           try{
               while(true){
                   System.out.println("Producing ::::: ");
                   blockingQueue.put("Value");
               }
           } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
           }
       }
   }

   public static class Consumer implements Runnable{
       private final BlockingQueue blockingQueue;

       public Consumer(BlockingQueue blockingQueue) {
           this.blockingQueue = blockingQueue;

       }

       public void consume(Object take){
           System.out.println(take.toString());
       }

       @Override
       public void run() {
           try{
               while(true){
                   System.out.println("Consuming ::::: ");
                   consume(blockingQueue.take());
               }
           } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
           }
       }
   }


   public static void main(String[] args){

       BlockingQueue blockingQueue = new LinkedBlockingQueue();
       Producer p  = new Producer(blockingQueue);
       Consumer c = new Consumer(blockingQueue);
       new Thread(p).start();
       new Thread(c).start();


   }

}
