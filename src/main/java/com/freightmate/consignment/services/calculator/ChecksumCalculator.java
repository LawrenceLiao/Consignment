package com.freightmate.consignment.services.calculator;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChecksumCalculator {

    public Integer calculateChecksum(List<Integer> indexList){

        int num1 = 0;
        int num2 = 0;
        int indexOfFirstEle = indexList.get(indexList.size() - 1);


        for(int i = indexList.size() - 1; i >= 0; i--) {
            if(Math.subtractExact(indexOfFirstEle, i) % 2 == 0) {
                num1 += indexList.get(i);
            } else {
                num2 += indexList.get(i);
            }
        }

        num1 *= 3;

        num2 *= 7;

        Integer sum = Math.addExact(num1, num2);

        Integer next = Math.addExact(sum, Math.subtractExact(10, sum % 10));

        return Math.subtractExact(next, sum);
    }
}
