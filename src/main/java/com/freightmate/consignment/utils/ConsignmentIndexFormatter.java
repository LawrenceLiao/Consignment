package com.freightmate.consignment.utils;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ConsignmentIndexFormatter {

    public List<Integer> formattingIndexAsPerDigits(Integer index, Integer digits ) {

        String stringifyIndex = String.valueOf(index);

        List<Integer> secondList = new ArrayList<>();

        for( char c : stringifyIndex.toCharArray()) {
            secondList.add(Integer.parseInt(String.valueOf(c)));
        }
        List<Integer> returnedList = new ArrayList<>(Arrays.stream(new int[digits - stringifyIndex.length()]).boxed().collect(Collectors.toList()));
        returnedList.addAll(secondList);
        return returnedList;
    }

    public String mapIndexListToString(List<Integer> indexList) {
        return indexList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

}
