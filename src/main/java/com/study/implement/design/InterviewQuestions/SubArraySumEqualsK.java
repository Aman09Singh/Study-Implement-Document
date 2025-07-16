package com.study.implement.design.InterviewQuestions;

public class SubArraySumEqualsK {

    public static int calculateSubArraysSumEqualsK(int[] arr, int k){

        int result = 0;
        int n = arr.length;

        for(int i=0;i<arr.length;i++){
            int prev = ((i-1 + n) % n);
            if((arr[i] + arr[prev] == k)){
                result++;
            }
        }

        return result;
    }

    public static void main(String[] args){

        int[] arr = {6,2,3,7,1,9};
        int k = 8;
        int result = calculateSubArraysSumEqualsK(arr, k);
        System.out.print(result);
    }
}
