package com.wxk.adventofcode.year2021;

import com.wxk.adventofcode.commons.InputParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day7 extends DayResolution {

    private final List<Integer> crabPositions = new ArrayList<>();

    public Day7() {
        super(7);
    }

    @Override
    public void makeCalculations() throws IOException {

//        String[] crabPositionString = readInputFile().replaceAll(WIN_EOL_DELIM,"").split(",");
        String[] crabPositionString = InputParser.getInputAsStringArrayComaDelimited(dayInput);
        for (String s : crabPositionString) {
            crabPositions.add(Integer.parseInt(s));
        }

        long minimumFuel = Long.MAX_VALUE;

        int maxPosition = crabPositions.stream()
                .mapToInt(v -> v)
                .max().orElse(0);

        for (int i = 0; i <= maxPosition; i++) {
            int tmpMinimum = calculateFuel(i,1);
            if (tmpMinimum < minimumFuel) {
                minimumFuel = tmpMinimum;
            }
        }

        firstResult = String.valueOf(minimumFuel);

        minimumFuel = Long.MAX_VALUE;

        for (int i = 0; i <= maxPosition; i++) {
            int tmpMinimum = calculateFuel(i,2);
            if (tmpMinimum < minimumFuel) {
                minimumFuel = tmpMinimum;
            }
        }

        secondResult = String.valueOf(minimumFuel);

    }

    private int calculateFuel(int position, int mode) {
        int res = 0;
        for (Integer crabPosition : crabPositions) {
            res += mode == 1 ? Math.abs(crabPosition - position) : summation(Math.abs(crabPosition - position));
        }
        return res;
    }

    private int summation(int num) {
        return num*(num+1)/2;
    }
}
