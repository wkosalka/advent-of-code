package com.wxk.adventofcode;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day6 extends DayResolution {

    private Map<Integer, Long> fishMap = new HashMap<>();

    public Day6() {
        super(6);
    }

    @Override
    public void makeCalculations() throws IOException {

        String inputList = getInputAsString().replaceAll(WIN_EOL_DELIM,"");

        initializeFishMap(inputList);
        firstResult = String.valueOf(iterateDays(80));

        initializeFishMap(inputList);
        secondResult = String.valueOf(iterateDays(256));
    }

    private void initializeFishMap(String inputList) {
        for (int i = 0; i < 9; i++) {
            fishMap.put(i, 0l);
        }
        String[] fishString = inputList.split(",");
        for (int i = 0; i < fishString.length; i++) {
            int currTimer = Integer.parseInt(fishString[i]);
            fishMap.put(currTimer, fishMap.get(currTimer) + 1);
        }
    }

    private long iterateDays(int days) {
        long res = 0l;
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
