package com.study.implement.design.Concurrency;

public class ConcurrencyPractice {

    private static int i = 1;

    //Write a simple program to print numbers 1 to 10 using two threads.
    public static class ThreadExample1{

        public ThreadExample1(){
            Runnable printer = () -> {
                while(true){
                    synchronized (ThreadExample1.class){
                        if(i > 10){
                            break;
                        }
                        System.out.println("ThreadName :: " + Thread.currentThread().getName()
                                + " " + i++);
                    }
                }
            };

            Thread t1 = new Thread(printer,"T1");
            Thread t2 = new Thread(printer,"T2");

            t1.start();t2.start();
//            t1.run();
        }
    }

//    public static void threadStates(){
////        NEW -> Runnable
////        runnable -> blocked
////        runnable -> waiting
////        waiting states -> Runnable
////                Runnable -> Terminated
//    }
//
//    //methods to make a method thread safe
//    public static void threadSafe(){
//            //use synchronised keyword
//            //use synchronised block
//        public synchronized void increment(){
////            counter++;;
//        }
//
//        public void increment(){
//            synchronized (this){
//                counter++;
//            }
//        }
//
//        //use ReEntrantLock
//        /// more flexible alternative to synchronized
//        private final ReentrantLock lock = new ReentrantLock();
//
//        public void increment() {
//            lock.lock();
//            try {
//                counter++;
//            } finally {
//                lock.unlock();
//            }
//        }
//
//
//    }

    ///  Preventing deadlock can be done through using synchornized lock order - or lock ordering
    /// simply meaning that you lock the objects utilised in the same order.
    /// try - and -timeout approach -
    /// there is a method called tryLock() that is used to release locks after a certain amount of time.
    /// lock1.tryLock(100, TimeUnit.MILLISECONDS
    /// You can also use higher level concurrency models like Executor Service)



    ///  Reentrant Locks In Java
    /// is a type of lock that allows the same thread to acquire the lock multiple times without causing deadlock.
    /// ReentrantLock is an explicit lock providing more control than synchornized.

//    public static void ReEntractExmpale(){
//        final ReentrantLock lock = new ReentrantLock();
//
//        void outer;() {
//            lock.lock();   // first acquisition
//            try {
//                System.out.println("Entered outer()");
//                inner();    // call a method that also locks
//            } finally {
//                lock.unlock(); // first release
//            }
//        }
//
//        void inner() {
//            lock.lock();   // same thread re-acquires the lock
//            try {
//                System.out.println("Entered inner()");
//            } finally {
//                lock.unlock(); // second release
//            }
//        }
//
//    }

    public static void main(String[] args){

        //create and start a thread
//        Thread t1 = new Thread(() -> {
//            System.out.println("Inside thread t1");
//        });
//        t1.start();


        ThreadExample1 threadExample1 = new ThreadExample1();

    }
}
