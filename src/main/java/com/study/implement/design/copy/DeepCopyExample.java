package com.study.implement.design.copy;

public class DeepCopyExample {

    static class Address{

        private String address;

        public Address(String address) {
            this.address = address;
        }
    }

    public static class Person{

        private int id;
        private String name;
        private Address address;

        public Person(String name, int id, Address address) {
            this.name = name;
            this.id = id;
            this.address = address;
        }

        //NOTE :: Having a copy constructor does not guarantee deep copy
        public Person(Person person1){
            this.name = person1.name;
            this.id = person1.id;
            this.address = new Address(person1.address.address);
            System.out.println("person has been copied");
        }
    }

    public static void main(String[] ags){

        Person p1 = new Person("name", 123, new Address("Bangalore"));
        Person p2 = new Person(p1);
        p2.name = "namep2";
        p2.address.address = "Mumbai";
        System.out.println("Person 1 ::  " + p1.name + " ID :: " + p1.id);
        System.out.println("Person 2 ::  " + p2.name + " ID :: " + p2.id + " Address :: " + p2.address.address);

    }
}
