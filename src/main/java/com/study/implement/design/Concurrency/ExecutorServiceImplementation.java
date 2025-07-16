package com.study.implement.design.Concurrency;

import java.util.concurrent.*;

public class ExecutorServiceImplementation {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Runnable task1 = () -> {
            System.out.println("Task1");
        };

        Runnable task2 = () -> {
            System.out.println("Task2");
        };

        executorService.submit(task1);
        executorService.submit(task2);


        Callable<String> callableTask = () -> {
            System.out.println("Inside Callable Task");
            return "SomeValue";
        };

        Future<String> future = executorService.submit(callableTask);
        System.out.println(future.get());


        executorService.shutdown();

    }
}
