package com.study.implement.design.InterviewQuestions;

import java.util.ArrayList;
import java.util.List;

public class KthLargestElementInArrayList {

    public static Integer KthLargestElement(List<Integer> integerList, int K){

        List<Integer> list = integerList.stream().sorted().toList();
        return list.get(list.size() - K + 1);

    }


    public static void main(String[] args){

        List<Integer> list  = new ArrayList<>(){{
            add(896);
            add(456);
            add(568695);
            add(234);
            add(2745);
            add(89345886);
            add(897866);
            add(34);
            add(657);
            add(23456);
            add(3);
            add(457);
        }};

        int K = (int) (Math.random() * (12));

        KthLargestElement(list, K);
        System.out.print( KthLargestElement(list, K));

    }
}
