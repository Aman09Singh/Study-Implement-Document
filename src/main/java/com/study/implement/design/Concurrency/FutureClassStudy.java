package com.study.implement.design.Concurrency;

import java.util.concurrent.*;

public class FutureClassStudy {

    /// A future Future<?> represents the result of an async computation
    /// something that's nto ready yet but will be available in the future.
    ///  java.util.concurrent
    ///

    public static void futureStudy() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<String> callable = ()-> {
            return "Callable will get result in the future";
        };

        Future<String> future = executorService.submit(callable);
        executorService.execute(()-> System.out.println("execute function used"));
        System.out.println(future.get());

        executorService.shutdown();

    }

    ///  Completable is introduced in Java 8
    /// used for async computation and getting the result of multiple of those
    /// enhanced version of Future
    /// benefits - non-blocking uses Optional instead of get() in future, chained computation,
    /// run tassks async - built in with runAsync and supplyAsync
    /// allow combining multiple async operations - using then(), thenCombine()
    /// callbacks, exception handling - using exceptionally
    ///


    public static class completableFutureStudy {

        public completableFutureStudy() throws ExecutionException, InterruptedException {
            //without return - use runAsync
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                System.out.println("Running in separate thread");
            });

            //with return - use SupplyAsync
            CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
                return 10;
            });

            try {
                int result = future1.get();
                System.out.println("SUpplyAsync Result :: " + result);
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }

            //Chaining
            CompletableFuture<String> stringCompletableFuture = CompletableFuture
                    .supplyAsync(()-> "hello")
                    .thenApply(s -> s + "asf")
                    .thenApply(String::toUpperCase);
            try{
                String string = stringCompletableFuture.get();
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            ExecutorService executorService = Executors.newFixedThreadPool(4);
            CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()->{
                return "Result from custom executor";
            },executorService);

            future2.thenApplyAsync(String::toUpperCase,executorService);

            System.out.println("Result: " + future2.get());

            executorService.shutdown();



        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        completableFutureStudy completableFutureStudy = new completableFutureStudy();

    }
}
