package com.study.implement.design.InterviewQuestions;

public class CircularDifferenceAndXOR {

    public static int getCircularDifferenceAndXOR(int[] A){

        int[] arrB = new int[A.length];
        int size = A.length;
        int totalSum = 0;

        for(int i = 0; i< size; i++){

            int prev = (i-1 + size) % size;
            int next = (i+1) % size;

            arrB[i] = Math.abs(A[prev] - A[next]);
        }

        for(int i=0;i<size;i++){
            totalSum += arrB[i] ^ i;
        }

        return totalSum;

    }
}
