package com.study.implement.design.InterviewQuestions;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Accolite_MapStreamQuestion {

    /**
     * Problem Statement
     * You are given two maps, you have to sort the second map and then
     * get the associated key from that value
     */
    public static void inputClass(){

        Map<Integer,String> hm1 = new HashMap<>();
        hm1.put(109,"String1");
        hm1.put(209,"String2");
        hm1.put(102,"String3");

        Map<String,Integer> hm2 = new HashMap<>();
        hm2.put("String1",67560);
        hm2.put("String2",123);
        hm2.put("String3",12356);

        hm2 = hm2.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1,e2) -> e1,
                        LinkedHashMap::new
                ));



    }

        public class TestClass {
            public static <Optional> void inputClass() {
                Map<Integer, String> hm1 = new HashMap<>();
                hm1.put(109, "String1");
                hm1.put(209, "String2");
                hm1.put(102, "String3");

                Map<String, Integer> hm2 = new HashMap<>();
                hm2.put("String1", 67560);
                hm2.put("String2", 123);
                hm2.put("String3", 12356);

                // Step 1: Sort hm2 by value
                Map<String, Integer> sortedHm2 = hm2.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new
                        ));

                // Step 2: For each sorted (name, value), find matching key in hm1
                for (Map.Entry<String, Integer> entry : sortedHm2.entrySet()) {
                    String name = entry.getKey();
                    Integer value = entry.getValue();

                    // Reverse lookup in hm1
                    Optional matchedKey = (Optional) hm1.entrySet()
                            .stream()
                            .filter(e -> e.getValue().equals(name))
                            .map(Map.Entry::getKey)
                            .findFirst();

                    ((java.util.Optional<?>) matchedKey).ifPresent(id ->
                            System.out.println("ID: " + id + ", Name: " + name + ", Value: " + value)
                    );
                }
            }


    public static void main(String[] args) {
    }
    }

}
