package com.study.implement.design.InterviewQuestions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindMissingKeyBoards {

    public static Integer getCountOfMissingKeyBoards(int k,List<Integer> inputList){

        Optional<Integer> max = inputList.stream().max(Integer::compareTo);
        Optional<Integer> min = inputList.stream().min(Integer::compareTo);
        int originalArraySize = 0;
        int keyboardLost;
        if (max.isPresent()) {
             originalArraySize = max.get() - min.get()  + 1;
        }
        keyboardLost = originalArraySize - inputList.size();

        return keyboardLost;
    }

    public static void main(String[] args){

        List<Integer> someList = new ArrayList<>() {{
            add(4);
            add(3);
            add(2);
            add(6);

        }};

        int result = getCountOfMissingKeyBoards(4,someList);
       System.out.println(result);

    }

}
