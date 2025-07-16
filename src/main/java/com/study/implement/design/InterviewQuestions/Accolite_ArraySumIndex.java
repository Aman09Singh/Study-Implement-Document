package com.study.implement.design.InterviewQuestions;

import java.util.HashMap;

public class Accolite_ArraySumIndex {

    /**
     * //find the index of array fro mwhere the sum of left side and the sum of right side of the array are equal
     */
    static void problemStatement() {

        int[] arr = {2, 3, 1, 4, 5, 2, 4, 3, 9, 12};

        //find the index of array fro mwhere the sum of left side and the sum of right side of the array are equal
        HashMap<Integer, Integer> hashMapL = new HashMap<>();
        HashMap<Integer, Integer> hashMapR = new HashMap<>();

        int sumL = 0, sumR = 0, L, R;
        int totalSum = 0;
        for (int i = 0; i < arr.length; i++) {
            totalSum += arr[i];
        }

        int index = 0;
        for (int i = 0, j = arr.length - 1; i < arr.length; i++, j--) {
            sumL += arr[i];
            sumR -= arr[j];
            if (totalSum - sumL == totalSum - sumR) {
                index = i;
            }
        }

        System.out.println("Index :: " + index);

    }

    public class aisolution {

        /**
         * Find the index in the array where the sum of elements to the left
         * is equal to the sum of elements to the right.
         */
        static void problemStatement() {
            int[] arr = {2, 3, 1, 4, 5, 2, 4, 3, 9, 12};
            int totalSum = 0;
            for (int num : arr) {
                totalSum += num;
            }

            int leftSum = 0;

            for (int i = 0; i < arr.length; i++) {
                int rightSum = totalSum - leftSum - arr[i];

                if (leftSum == rightSum) {
                    System.out.println("Equilibrium index: " + i);
                    return;
                }

                leftSum += arr[i];
            }

            System.out.println("No equilibrium index found.");
        }

    }
}
