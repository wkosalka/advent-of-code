package com.wxk.adventofcode.year2021;

import com.wxk.adventofcode.commons.InputParser;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.*;

public class Day16 extends DayResolution {

    public Day16() {
        super(16);
    }

    private int versionSum = 0;
    private String binary = "";

    private int idx = 0;

    @Override
    public void makeCalculations() throws IOException {

        String input = InputParser.getInputAsSingleString(dayInput);
        StringBuilder binaryBuilder = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            String binaryPart = StringUtils.leftPad(Integer.toBinaryString(Integer.parseInt(""+input.charAt(i),16)), 4, "0");
            binaryBuilder.append(binaryPart);
        }
        binary = binaryBuilder.toString();

        long result = 0;
        boolean finished = false;
        while (!finished) {
            result += decodePackage();
            if (idx == binary.length() || Integer.parseInt(binary.substring(idx),2) == 0) finished = true;
        }
        firstResult = String.valueOf(versionSum);
        secondResult = String.valueOf(result);
    }

    private long decodePackage() {

        versionSum += Integer.parseInt(binary.substring(idx,idx+3),2);
        idx += 3;
        int type = Integer.parseInt(binary.substring(idx,idx+3),2);
        idx += 3;
        Deque<Long> valuesDeque = new ArrayDeque<>();
        if (type == 4) {
            String subPacket;
            StringBuilder subBinary = new StringBuilder();
            do {
                subPacket = binary.substring(idx,idx+5);
                idx += 5;
                subBinary.append(subPacket.substring(1));

            } while (subPacket.charAt(0) != '0');
            return Long.parseLong(subBinary.toString(),2);
        } else {
            char lengthType = binary.charAt(idx);
            idx++;
            switch (lengthType) {
                case '0' :
                    int length = Integer.parseInt(binary.substring(idx,idx+15),2);
                    idx += 15;

                    int boundaryIdx = idx + length;
                    while (idx < boundaryIdx) {
                        valuesDeque.add(decodePackage());
                    }
                    break;
                case '1' :
                    int count = Integer.parseInt(binary.substring(idx,idx+11),2);
                    idx += 11;

                    for (int i = 0; i < count; i++) {
                        valuesDeque.add(decodePackage());
                    }
                    break;
                default :
                    break;
            }
        }
        return makeOperations(type, valuesDeque);
    }

    private long makeOperations(int type, Deque<Long> valuesDeque) {

        long tmp1;
        long tmp2;
        long result = 0;
        switch (type) {
            case 0:
                while (!valuesDeque.isEmpty()) {
                    result += valuesDeque.pop();
                }
                break;
            case 1:
                result = 1;
                while (!valuesDeque.isEmpty()) {
                    result *= valuesDeque.pop();
                }
                break;
            case 2:
                result = Collections.min(valuesDeque);
                break;
            case 3:
                result = Collections.max(valuesDeque);
                break;
            case 5:
                tmp1 = valuesDeque.pop();
                tmp2 = valuesDeque.pop();
                result = tmp1 > tmp2 ? 1 : 0;
                break;
            case 6:
                tmp1 = valuesDeque.pop();
                tmp2 = valuesDeque.pop();
                result = tmp1 < tmp2 ? 1 : 0;
                break;
            case 7:
                tmp1 = valuesDeque.pop();
                tmp2 = valuesDeque.pop();
                result = tmp1 == tmp2 ? 1 : 0;
                break;
            default:
                break;
        }
        return result;
    }
}
