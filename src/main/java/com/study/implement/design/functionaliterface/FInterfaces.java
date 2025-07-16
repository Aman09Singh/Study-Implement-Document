package com.study.implement.design.functionaliterface;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FInterfaces {

    //predicate
    //function
    //consumer
    static Predicate<Integer> predicate = new Predicate<Integer>() {
        @Override
        public boolean test(Integer integer) {
            return integer % 2 == 0;
        }
    };

    static Function<Integer,String> function = new Function<Integer, String>() {
        @Override
        public String apply(Integer integer) {
            return "yaas " + integer;
        }
    };

    static Consumer<Integer> consumer =  new Consumer<Integer>() {
        @Override
        public void accept(Integer integer) {
            System.out.println("This integer :: " + integer + " has been consumed");
        }
    };


    public static void main(String[] args){

        System.out.println("Predicate :: " + predicate.test(231));
        System.out.println("Function :: " + function.apply(12));
        consumer.accept(123);
    }

}
