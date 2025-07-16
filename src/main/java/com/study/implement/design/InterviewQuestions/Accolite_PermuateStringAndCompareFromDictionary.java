package com.study.implement.design.InterviewQuestions;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Accolite_PermuateStringAndCompareFromDictionary {

    /**
     * generate all the words out of string, words should be of same length as of the input
     * verify the generated words from a dictionary
     */

    static void problemStatement() {


        String input = "asf";
        int size = input.length();
//populated set with characters in the string.
        Set<Character> stringSet = new HashSet<>();
        for (char c : input.toCharArray()) {
            stringSet.add(c);
        }

//generate 10 random strings from the characters and add then to a list
        List<String> finalList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            StringBuilder stringBuilder = new StringBuilder();
            while (stringBuilder.length() <= size) {

                int randomNumber = ThreadLocalRandom.current().nextInt(1, stringSet.size());
                stringBuilder.append(stringSet.stream().toList().get(randomNumber));

            }
            finalList.add(stringBuilder.toString());
        }

        finalList.forEach(System.out::println);

    }

    public class ai_solution {

        /**
         * Generate all the permutations of the input string (same length),
         * and validate them against a dictionary of valid words.
         */
        static void problemStatement() {
            String input = "asf";
            Set<String> dictionary = new HashSet<>(Arrays.asList("asf", "afs", "fas", "cat", "dog", "saf"));

            Set<String> permutations = new HashSet<>();
            boolean[] used = new boolean[input.length()];
            backtrack(input, new StringBuilder(), used, permutations);

            System.out.println("Valid words from dictionary:");
            for (String word : permutations) {
                if (dictionary.contains(word)) {
                    System.out.println(word);
                }
            }
        }

        // Backtracking function to generate all permutations
        private static void backtrack(String input, StringBuilder current, boolean[] used, Set<String> result) {
            if (current.length() == input.length()) {
                result.add(current.toString());
                return;
            }

            for (int i = 0; i < input.length(); i++) {
                if (used[i]) continue;
                used[i] = true;
                current.append(input.charAt(i));
                backtrack(input, current, used, result);
                current.deleteCharAt(current.length() - 1);
                used[i] = false;
            }
        }

    }
}
