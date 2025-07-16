package com.study.implement.design.Concurrency;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerUsingWaitAndNotify {

    private static final int CAPACITY = 5;
    private final Object lock = new Object();
    private final Queue<Integer> buffer = new LinkedList<>();

    public void produce(){
        int value = 0;
        while(true){
            synchronized (lock){
                while(buffer.size() == CAPACITY){
                    try{
                        System.out.println("Buffer Full. Producer Waiting");
                        lock.wait();
                    }catch(InterruptedException e){
                        Thread.currentThread().interrupt();
                    }
                }

                buffer.add(value);
                System.out.println("Value added ::  " + value++);
                lock.notify();
            }

            try{
                Thread.sleep(500);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }

    public void consume(){
        while(true){
            synchronized (lock){
                while(buffer.isEmpty()){
                    try{
                        System.out.println("Buffer Empty, Waiting for Producer");
                        lock.wait();
                    }catch(InterruptedException e){
                        Thread.currentThread().interrupt();
                    }
                }

                int bufferLoad = buffer.poll();
                System.out.println("Value :: " + bufferLoad + " has been consumed");
                lock.notify();
            }
            try{
                Thread.sleep(500);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args){

        ProducerConsumerUsingWaitAndNotify producerConsumerUsingWaitAndNotify = new ProducerConsumerUsingWaitAndNotify();
        Thread producer = new Thread(producerConsumerUsingWaitAndNotify::produce);
        Thread consumer = new Thread(producerConsumerUsingWaitAndNotify::consume);

        producer.start();
        consumer.start();

    }
}
