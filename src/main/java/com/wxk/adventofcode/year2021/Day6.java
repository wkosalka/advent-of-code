package com.wxk.adventofcode.year2021;


import com.wxk.adventofcode.commons.InputParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day6 extends DayResolution {

    private final Map<Integer, Long> fishMap = new HashMap<>();

    public Day6() {
        super(6);
    }

    @Override
    public void makeCalculations() throws IOException {

        String inputList = InputParser.getInputAsSingleString(dayInput);

        initializeFishMap(inputList);
        firstResult = String.valueOf(iterateDays(80));

        initializeFishMap(inputList);
        secondResult = String.valueOf(iterateDays(256));
    }

    private void initializeFishMap(String inputList) {
        for (int i = 0; i < 9; i++) {
            fishMap.put(i, 0L);
        }
        String[] fishString = inputList.split(",");
        for (String s : fishString) {
            int currTimer = Integer.parseInt(s);
            fishMap.put(currTimer, fishMap.get(currTimer) + 1);
        }
    }

    private long iterateDays(int days) {
        long res = 0L;
        for (int day = 1; day <= days; day++) {
            long newToCreate = fishMap.get(0);
            for (int i = 0; i < 8; i++) {
                fishMap.put(i, fishMap.get(i + 1));
            }
            fishMap.put(8,newToCreate);
            fishMap.put(6,fishMap.get(6) + newToCreate);
        }

        for (int i = 0; i < 9; i++) {
            res += fishMap.get(i);
        }
        return res;
    }
}
