package com.study.implement.design.CreationalPatterns;

public class SingleTon {

    /**
     * Thread Safe
     * Eager Initialization as the instance is initialized when created.
     * Available, but might not be used ever.
     */
    public static class SingleTonThreadSafeImplementation{
        private static final  SingleTonThreadSafeImplementation instance = new SingleTonThreadSafeImplementation();

        public SingleTonThreadSafeImplementation() {
        }

        public static SingleTonThreadSafeImplementation getInstance(){
            return instance;
        }
    }

    /**
     * lazy loading, not thread safe
     */
    public static class SingletonImplementation{
       private static SingletonImplementation instance;

        public SingletonImplementation() {
        }

        public static SingletonImplementation getInstance(){
            if(instance == null){
                instance = new SingletonImplementation();
            }
            return instance;
        }
    }

    /** Using synchronized to make the instance thread safe.
     *
     */
    public static class SingletonThreadSafeImplementation{

        private static SingletonThreadSafeImplementation instance;

        public SingletonThreadSafeImplementation() {
        }

        public static synchronized SingletonThreadSafeImplementation getInstance(){
            if(instance == null){
                instance = new SingletonThreadSafeImplementation();
            }
            return instance;
        }
    }

    /**
     * once the getInstance is called, then the class gets synchronised.
     * And from that point, the instance is shared within different threads
     * accessing the volatile instance once at a time.
     * Thread-safe, fast, and lazy-loaded
     * This is a thread safe singleton with double-checked locking
     */
    public static class SingletonDoubleCheckingLockingImplementation{

        private static volatile SingletonDoubleCheckingLockingImplementation instance;

        private SingletonDoubleCheckingLockingImplementation(){
    //This private constructor disallow objects to be made from outside
        }

        public static SingletonDoubleCheckingLockingImplementation getInstance(){
            if(instance == null){ //first check with no locking
                synchronized (SingletonDoubleCheckingLockingImplementation.class){
                    if(instance == null){ //second check with locking
                        instance = new SingletonDoubleCheckingLockingImplementation();
                    }
                }
            }
            return instance;
        }
    }

    /**
     * Lazy-loaded
     *
     * Thread-safe
     *
     * No synchronization overhead
     */
    public static class BillPughSingletonImplementation{

        public BillPughSingletonImplementation() {
        }

        private static class Holder{
            private static final BillPughSingletonImplementation INSTANCE  = new BillPughSingletonImplementation();
        }

        public static BillPughSingletonImplementation getInstance(){
            return Holder.INSTANCE;
        }
    }

    /**
     * Enum guarantees one instance
     *
     * Serialization-safe
     *
     * Reflection-safe
     */
    public static enum SingletonEnum{
        INSTANCE;

        public void doSomething(){

        }
    }

    public static void main(String[] asdf){
        SingletonEnum instance = SingletonEnum.INSTANCE;
        instance.doSomething();
    }

}
