package com.study.implement.design.InterviewQuestions;

public class SmallestSubArrayWithSumGreaterThanK {

    public static void calculateSmallestSubArray(int[] arr, int k){

        int L=0;
        int R = 0;
        int sum = 0;
        int n = arr.length;
        int minLength = n+1;

        while(R < n){

            while(sum <= k && R<n){
                sum += arr[R++];
            }

            while(sum > k && L < n){
                minLength = Math.min(minLength, R-L);
                sum -= arr[L++];
            }
        }

        System.out.println(L);
        System.out.println(R);
//        return minLength == 0 ? 0 : minLength;
    }


    public static void main(String[] args){

        int[] arr = {3,4,5,2,6,8,2,6}
        ;
         calculateSmallestSubArray(arr, 8);
    }

}
