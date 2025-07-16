package com.study.implement.design.InterviewQuestions;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicateWordsInASentence {

    public static List<String> getDuplicateWordsInAString(String input){

        Map<String, Integer> storeHashMap = Arrays.stream(input.split(" "))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(
                        word -> word,
                        Collectors.collectingAndThen(Collectors.counting(),Long::intValue)
                ));

        return storeHashMap.keySet().stream()
                .filter(key -> storeHashMap.get(key) > 1)
                .toList();
    }

    public static void main(String[] args){

        String input = "this string contains contains duplicate duplicate words words words";
        List<String> output = getDuplicateWordsInAString(input);
        output.forEach(System.out::println);


    }

}
