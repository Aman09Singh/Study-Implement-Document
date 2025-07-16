package com.study.implement.design.InterviewQuestions;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSubArray {

    public static void main(String[] args) {

        int[] arr = {100,4,200,1,3,2,6,7,8,9,10,11};
        int maxLength = 0;

        Set<Integer> set = new HashSet<>();
        for(int a : arr){
            set.add(a);
        }

        for(int num : set){
            //new sequence
            if (!set.contains(num - 1)) {

                int currentNum = num;
                int length = 1;

                while(set.contains(currentNum + 1)){
                    currentNum++;
                    length++;
                }
                maxLength = Math.max(length,maxLength);
            }

        }

        System.out.println("MaxLength :: " + maxLength);

    }

}
