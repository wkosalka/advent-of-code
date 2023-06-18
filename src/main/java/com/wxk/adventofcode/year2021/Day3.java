package com.wxk.adventofcode.year2021;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Day3 extends DayResolution {

    public Day3() {
        super(3);
    }

    @Override
    public void makeCalculations() throws IOException {

        List<String> inputList = getInputAsStringList();

        String gammaRate = getGammaRate(inputList);
        String epsilonRate = getEpsilonRate(inputList);
        firstResult = String.valueOf(Integer.parseInt(gammaRate,2) * Integer.parseInt(epsilonRate,2));

        for (int i = 0; i < 12; i++) {
            char p01 = getMostAtPosition(inputList,i);
            inputList = filterByPosDigit(inputList, i, p01);
            if (inputList.size() == 1) break;
        }

        String o2GenRating = inputList.get(0);

        inputList = getInputAsStringList();

        for (int i = 0; i < 12; i++) {
            char p01 = getLeastAtPosition(inputList,i);
            inputList = filterByPosDigit(inputList, i, p01);
            if (inputList.size() == 1) break;
        }

        String co2ScrubRating = inputList.get(0);

        secondResult = String.valueOf(Integer.parseInt(o2GenRating,2) * Integer.parseInt(co2ScrubRating,2));
    }

    private String getGammaRate(List<String> inList) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            result.append(getMostAtPosition(inList, i));
        }
        return result.toString();
    }

    private String getEpsilonRate(List<String> inList) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            result.append(getLeastAtPosition(inList, i));
        }
        return result.toString();
    }

    private char getMostAtPosition(List<String> inList, int position) {
        int cnt = 0;
        char res = '1';
        for (String listElem : inList) {
            if (listElem.charAt(position) == '1') cnt++;
        }
        if (cnt < (double) inList.size()/2) res = '0';
        return res;
    }

    private char getLeastAtPosition(List<String> inList, int position) {
        int cnt = 0;
        char res = '1';
        for (String listElem : inList) {
            if (listElem.charAt(position) == '1') cnt++;
        }
        if (cnt >= (double)inList.size()/2) res = '0';
        return res;
    }

    private List<String> filterByPosDigit(List<String> inList, int position, char digit) {
        List<String> resList = new LinkedList<>();
        for (String listElem : inList) {
            if (listElem.charAt(position) == digit) resList.add(listElem);
        }
        return resList;
    }
}
