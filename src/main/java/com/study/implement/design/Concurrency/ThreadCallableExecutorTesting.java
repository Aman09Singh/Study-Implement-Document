package com.study.implement.design.Concurrency;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadCallableExecutorTesting {

    public static final AtomicInteger lineCounter = new AtomicInteger(0);
    public static final String PATH = "output/testdata.csv";
    public static final Integer threadCount = 1;

    public static class CountIdNameProcessor implements Runnable{

        private final String line;

        public CountIdNameProcessor(String line) {
            this.line = line;
        }

        @Override
        public void run() {
            try{
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                int currentCount = lineCounter.incrementAndGet();
//            System.out.printf("Processed #%d → ID: %d, Name: %s%n", currentCount, id, name);

            }catch (Exception e){

            }
        }
    }

    public static class CallableReturnId implements Callable<Integer> {

        public final String line;

        public CallableReturnId(String line) {
            this.line = line;
        }


        @Override
        public Integer call() throws Exception {

            try{
                String[] parts = line.split(",");
                return Integer.parseInt(parts[0].trim());
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void createFile(){
        String filepath ="output/testdata.csv";
        int rows = 100000;

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))){

            writer.write("ID,Name,Age,Email\n");

            for(int i = 1;i<=rows;i++){
                int id  = i;
                String name = getRandomName();
                int age = ThreadLocalRandom.current().nextInt(18,60);
                String email = name.toLowerCase() + "@example.com";

                writer.write(id + "," + name + "," + age + "," + email + "\n");
            }

            System.out.println("CSV file created. ");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getRandomName(){
        UUID randomNameString = UUID.randomUUID();
        return randomNameString.toString().replaceAll("-","").substring(0,7);
    }



    public static void processCSVWithThreads(String filename){

        //Runnable Thread
        //I get the values form the line about id and name and return it
        //print count of lines processed and the name and id processed
        Instant startTime = Instant.now();
        List<Thread> threadList = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){

            String line;
            reader.readLine();

            while((line = reader.readLine()) != null){
                Thread thread = new Thread(new CountIdNameProcessor(line));
                thread.start();
                threadList.add(thread);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(Thread t : threadList){
            try{
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("All lines processed. in :: :: " + Duration.between(startTime,Instant.now()).toMillis());
    }

    //Direct invocation is not possible
    //I htink main thrad is processing the whole process, no sub threads are getting made here.
    public static void processFileUsingCallableReturningAListWithIds(String fileName){

        List<Integer> getListofIds = new ArrayList<>();
        Instant startTime = Instant.now();
        try(BufferedReader reader =  new BufferedReader(new FileReader(fileName))) {

            String line;
            reader.readLine();

            while((line = reader.readLine()) !=null){
                CallableReturnId value = new CallableReturnId(line);
                Integer v = value.call();
                getListofIds.add(v);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        System.out.println("All lines processed. in :: :: " + Duration.between(startTime,Instant.now()).getNano());
        System.out.println("Count :: " + getListofIds.size());
    }

    public static void processFileUsingExecutorThreadRunnable(String filepath, Integer threadCount){

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        Instant startTime = Instant.now();
        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            //skips the first line
            String line;
            reader.readLine();
            List<Future> futures = new ArrayList<>();

            while((line = reader.readLine()) != null){
                executorService.submit(new CountIdNameProcessor(line));
            }
            System.out.println("All lines processed. in :: :: " + Duration.between(startTime,Instant.now()).getNano());
            executorService.shutdown();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void processFileUsingExecutorThreadCallable(String filepath, Integer threadCount){

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        List<Integer> idList = new ArrayList<>();
        Instant startTime = Instant.now();
        List<Callable<Integer>> tasks = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            //skips the first line
            String line;
            reader.readLine();

            while((line = reader.readLine()) != null){
                tasks.add(new CallableReturnId(line)); // groups the tasks in a collections
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            executorService.shutdown();
        }

        List<Future<Integer>> list = new ArrayList<>();
        try {
            list = executorService.invokeAll(tasks); //passes the task to be invoked parallelly
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for(Future f : list){
            try{
                Integer id = (Integer) f.get();
                idList.add(id);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("All lines processed. in :: :: " + Duration.between(startTime,Instant.now()).getNano());
        System.out.println("Id LIst count :: " + idList.size());
    }

    public static void processFileUsingExecutorThreadCompletableFuture(String filepath, Integer threadCount){

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        Instant startTime = Instant.now();
        List<String> lines = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            //skips the first line
            String line;
            reader.readLine();
            while((line = reader.readLine()) != null){
               //add all line
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        List<CompletableFuture<Integer>> future = lines.stream()
                .map(line -> CompletableFuture.supplyAsync(() -> {
                    try{
                        return Integer.parseInt(line.split(",")[0].trim());
                    }catch (NumberFormatException e){
                        throw new RuntimeException("Invalid Line" + line);
                    }
                },executorService))
                .toList();

        CompletableFuture.allOf(future.toArray(new CompletableFuture[0])).join();
        //NOTE You're not passing index 0 as a "starting point" — you're passing an empty array of the correct type to help Java know how to construct the result array.

        List<Integer> id = future.stream().map(CompletableFuture::join)
                .toList();

        executorService.shutdown();

        System.out.println("All lines processed. in :: :: " + Duration.between(startTime,Instant.now()).getNano());
        System.out.println("Id LIst count :: " + id.size());
    }




    public static void main(String[] args){

        //callable
        //runnable
        //completablefuture
        //executor service
        //thread
        //futuretask

        createFile();
        File file = new File(PATH);
        if(file.exists()){
//            processCSVWithThreads(PATH);
//            processFileUsingCallableReturningAListWithIds(PATH);
//            processFileUsingExecutorThreadRunnable(PATH,5);
//            processFileUsingExecutorThreadCallable(PATH,5);
            processFileUsingExecutorThreadCompletableFuture(PATH,5);

        }

    }
}
