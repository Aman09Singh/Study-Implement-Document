package com.study.implement.design.InheritanceQuestions;

public class PolymorphismQuestions1 {

    public static class Bird{
        int count = 90;

        void fly(){
            System.out.println(count + "bidrds flying");
        }

        void eat(){
            System.out.println(count + "Birds Eating");
        }
    }

    public static class Penguin extends Bird{

        int count =70;
        void swim(){
            System.out.println(count + "Swimming Penguin");
        }

        void eat(){
            System.out.println(count + "Penguing eating");
        }

    }

    public static void main(String[] args) {

        Penguin bird = new Penguin();
        bird.swim();
        bird.eat();
        System.out.println(bird.count + "Bird are there");

    }
}
