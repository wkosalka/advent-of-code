package com.wxk.adventofcode.year2022;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.wxk.adventofcode.commons.InputParser.getInputAsStringArray;

public class Day1 extends DayResolution {

    public Day1() {
        super(1);
    }

    @Override
    public void makeCalculations() throws IOException {
        String[] input = getInputAsStringArray(dayInput);
        List<Integer> elfList = getElfList(input);
        elfList.sort(Collections.reverseOrder());
        firstResult = elfList.get(0).toString();
        secondResult = String.valueOf(elfList.stream().limit(3).mapToInt(Integer::intValue).sum());
    }

    private List<Integer> getElfList(String[] input) {
        List<Integer> elfList = new ArrayList<>();
        int currFood = 0;
        for (var food : input) {
            if (food.length() > 0) {
                currFood += Integer.parseInt(food);
            } else {
                if (currFood > 0) {
                    elfList.add(currFood);
                }
                currFood = 0;
            }
        }

        return elfList;
    }
}
