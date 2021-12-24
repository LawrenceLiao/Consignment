package com.freightmate.consignment.services.calculator;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChecksumCalculator {

    public Integer calculateChecksum(List<Integer> indexList){
        Integer num1 = 0;
        Integer num2 = 0;

        for(int i = indexList.size() - 1; i >= 0; i -= 2) {
            num1 += indexList.get(i);
        }
        num1 *= 3;

        for(int j = indexList.size() - 2; j >= 0; j -= 2) {
            num2 += indexList.get(j);
        }
        num2 *= 7;

        Integer sum = Math.addExact(num1, num2);

        Integer next = Math.addExact(sum, Math.subtractExact(10, sum % 10));

        return Math.subtractExact(next, sum);
    }
}
