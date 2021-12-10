package com.wxk.adventofcode;

import java.io.IOException;

public class Day1 extends DayResolution {

    public Day1() {
        super(1);
    }

    @Override
    public void makeCalculations() throws IOException {
        int[] numArrays = getInputAsIntArray();

        int increaseCount = 0;
        for (int i = 1; i < numArrays.length; i++) {
            if (numArrays[i] > numArrays[i-1]) increaseCount++;
        }

        firstResult = String.valueOf(increaseCount);

        increaseCount = 0;
        for (int i = 3; i < numArrays.length; i++) {
            if (numArrays[i] + numArrays[i-1] + numArrays[i-2] > numArrays[i-1] + numArrays[i-2] + numArrays[i-3]) {
                increaseCount++;
            }
        }

        secondResult = String.valueOf(increaseCount);
    }
}
