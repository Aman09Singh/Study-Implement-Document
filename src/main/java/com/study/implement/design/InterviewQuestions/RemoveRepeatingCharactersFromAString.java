package com.study.implement.design.InterviewQuestions;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RemoveRepeatingCharactersFromAString {

    public static void removeRepeatingCharactersFromString(String input){

        String[] arr = input.split(" ");
        String result = Arrays.stream(arr)
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet()
                .stream().filter(key -> (key.getValue() != 1))
                .map(Map.Entry::getKey)
                .collect(Collectors.joining(" "));

        System.out.println(result);

    }

    public static void main(String[] args){

        String input = "this string contains contains duplicate duplicate words words words";
        removeRepeatingCharactersFromString(input);

    }

}
