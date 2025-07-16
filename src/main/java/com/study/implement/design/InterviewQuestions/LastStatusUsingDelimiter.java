package com.study.implement.design.InterviewQuestions;

import java.util.List;

public class LastStatusUsingDelimiter {

    public static String getDelimiter(String input){

        List<String> result = List.of(input.split(";"));
        return result.get(result.size()-1);
    }

    public static void main(String[] args){
        System.out.print(getDelimiter("returned;on-hold"));
    }
}
