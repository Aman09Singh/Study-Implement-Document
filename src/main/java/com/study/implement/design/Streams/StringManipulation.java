package com.study.implement.design.Streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringManipulation {

    ///
    /// Get Count of Characters
    public static Map<Character,Long> function_1(String input){

        return input.chars()
                .mapToObj(c -> (char)c)
                .filter(c -> c != ' ')
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

    }

    public static String function_2(String input){

        String collect = input.chars().mapToObj(c -> (char) c)
                .filter(c -> !isVowel(c)) //filter out characters that are not vowel.
                .map(String :: valueOf)
                .collect(Collectors.joining());
        return collect;

    }

    public static boolean isVowel(Character c){
        if("aeiouAEIOU".contains(c.toString())){
            return true;
        }
        return false;
    }

//    public static String function_3(String input){
//
////        String result = Arrays.stream(input.split(" "))
////                .map(word -> )
//
////    }?


    public static void main(String[] args){

//        String stringInput_1 = "count_ the number of charactesr";
//        System.out.println(function_1(stringInput_1));
//
//        String stringInput_2 = "Education is important";
//        System.out.println(function_2(stringInput_2));
//
//        String stringInput_3 = "Reverse Characters of Each Word";
////        System.out.println("F3 :::: "  + function_3(String input));

        //group employee by department
        Map<String,List<Employee>> groupByDepartment = EmployeeData.getEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment)); //takes input as Function, input is the Employee objet and output is the getDepartment,
        //from there it collects it in a map
//        groupByDepartment.entrySet().stream().forEach(System.out::println);

        //highest paid employee in each department
        Map<String, Optional<Employee>> groupByHighestSalaryInDepartment  = EmployeeData.getEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparing(Employee::getSalary))));

//        groupByHighestSalaryInDepartment.entrySet().stream().forEach(employee -> {
//            System.out.println(employee.getValue().get());
//        });

        List<Employee> sortInDescendingOrder = EmployeeData.getEmployees().stream()
                .sorted(Comparator.comparing(Employee::getSalary)).toList().reversed();
//        sortInDescendingOrder.forEach(employee -> System.out.println(employee.getName() + employee.getSalary()));


        List<Integer> numbers = Arrays.asList(5, 12, 7, 5, 9, 12, 18, 3, 7, 15, 20, 12);


        //find duplicate elements in a list of integer
        //group the numebrs by occurence -> then on the entryset i filter if value is greater than 1 then get the key add to set
        Set<Integer> duplicateElements = numbers.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
//        duplicateElements.forEach(System.out::println);

        //Flatten a list of lists into a single list.
        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9),
                Arrays.asList(10, 11)
        );

        List<Integer> flattendList = listOfLists.stream()
                .flatMap(Collection::stream)
                .toList();
//        flattendList.forEach(System.out::println);

        //Partition a list of integers into even and odd.
        Map<Boolean, List<Integer>> partitionList = numbers.stream()
                .distinct()
                .collect(Collectors.partitioningBy(p -> p%2 ==0));
//        partitionList.entrySet().forEach(System.out::println);

//        Count the frequency of each word in a given string.
        String text = "Java is great and Java is powerful but Java is also verbose";

        Map<String, Long> stringFrequency = Arrays.stream(text.split(" "))
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
//        stringFrequency.entrySet().forEach(System.out::println);

        //Remove duplicate characters from a string while maintaining order.
        String input = "programming";

        String duplicateChar = input.chars()
                .mapToObj(c -> (char) c)
                .distinct()
                .map(String::valueOf)
                .collect(Collectors.joining());

//        Find all transactions above ₹10000 from a list of Transaction objects.
        List<Transaction> transactions = TransactionData.getTransactions()
                .stream()
                .filter(transaction -> transaction.getAmount() > 10000)
                .toList();
//        transactions.forEach(System.out::println);

        List<String> strings1 =  Arrays.asList(
                "apple",
                "banana",
                "cherry",
                "date",
                "fig",
                "grape"
        );

//        Create a Map<String, Integer> from a list of strings containing each string and its length.
        Map<String,Integer> stringIntegerMap = strings1.stream()
                .collect(Collectors.toMap(Function.identity(), String::length));

        stringIntegerMap.entrySet().stream().forEach(System.out::println);

        List<Student> students = List.of(
                new Student("Alice", "Grade 10", 88),
                new Student("Bob", "Grade 10", 95),
                new Student("Charlie", "Grade 9", 72),
                new Student("David", "Grade 10", 91),
                new Student("Eva", "Grade 9", 85),
                new Student("Frank", "Grade 8", 78),
                new Student("Grace", "Grade 8", 89),
                new Student("Hannah", "Grade 9", 90)
        );

        Map<String, Optional<Student>> toppersByGrade = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getGrade,
                        Collectors.maxBy(Comparator.comparingInt(Student::getMarks))
                ));

        String str1 = "listen";
        String str2 = "silent";

        var characterIntegerMap = str1.chars()
                .mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        var characterIntegerMap1 = str2.chars()
                .mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        characterIntegerMap.equals(characterIntegerMap1); //-> gives result
    }

    static class Student {
        String name;
        String grade;
        int marks;

        public Student(String name, String grade, int marks) {
            this.name = name;
            this.grade = grade;
            this.marks = marks;
        }

        // Getters (optional depending on how you're accessing fields)
        public String getName() { return name; }
        public String getGrade() { return grade; }
        public int getMarks() { return marks; }
    }



    public static class TransactionData {
        public static List<Transaction> getTransactions() {
            return Arrays.asList(
                    new Transaction("TXN001", "Alice", 9500.00, "2024-12-01"),
                    new Transaction("TXN002", "Bob", 12500.00, "2025-01-15"),
                    new Transaction("TXN003", "Charlie", 18000.00, "2025-03-22"),
                    new Transaction("TXN004", "David", 4000.00, "2025-04-10"),
                    new Transaction("TXN005", "Eva", 21000.00, "2025-05-05")
            );
        }
    }

    public static class Transaction {
        private String id;
        private String user;
        private double amount;
        private String date; // or use LocalDate

        public Transaction(String id, String user, double amount, String date) {
            this.id = id;
            this.user = user;
            this.amount = amount;
            this.date = date;
        }

        // Getters
        public String getId() {
            return id;
        }

        public String getUser() {
            return user;
        }

        public double getAmount() {
            return amount;
        }

        public String getDate() {
            return date;
        }

        @Override
        public String toString() {
            return id + " - " + user + " - ₹" + amount + " - " + date;
        }
    }


    public static class Employee {
        private int id;
        private String name;
        private String department;
        private double salary;

        public Employee(int id, String name, String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        // Getters (no setters for immutability)
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public double getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return name + " (" + id + ")";
        }
    }


    public static class EmployeeData {
        public static List<Employee> getEmployees() {
            return Arrays.asList(
                    new Employee(1, "Alice", "Engineering", 80000),
                    new Employee(2, "Bob", "HR", 50000),
                    new Employee(3, "Charlie", "Engineering", 85000),
                    new Employee(4, "David", "Sales", 60000),
                    new Employee(5, "Eva", "Engineering", 95000),
                    new Employee(6, "Frank", "Sales", 55000),
                    new Employee(7, "Grace", "HR", 52000),
                    new Employee(8, "Hank", "Marketing", 70000)
            );
        }
    }



}
