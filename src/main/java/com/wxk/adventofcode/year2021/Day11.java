package com.wxk.adventofcode.year2021;

import java.io.IOException;

public class Day11 extends DayResolution {
    public Day11() {
        super(11);
    }

    private int[][] inputArray;
    private int numOfFlashes = 0;

    @Override
    public void makeCalculations() throws IOException {

        inputArray = getInputAsIntIntArray();

        for (int x = 0; x < 100; x++) {
            boolean hasChanged = true;
            increaseEnergy();
            while (hasChanged) {
                hasChanged = processStep();
            }
        }

        firstResult = String.valueOf(numOfFlashes);
        inputArray = getInputAsIntIntArray();

        int allFlashStep = 0;
        boolean areAllFlashing = false;
        while (!areAllFlashing) {
            allFlashStep++;
            boolean hasChanged = true;
            increaseEnergy();
            while (hasChanged) {
                hasChanged = processStep();
            }
            if (isAllFlashing()) {
                areAllFlashing = true;
            }
        }

        secondResult = String.valueOf(allFlashStep);
    }

    private void increaseEnergy() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                inputArray[x][y] = ++inputArray[x][y];
            }
        }
    }

    private boolean processStep() {
        boolean hasChanged = false;
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if (inputArray[x][y] > 9) {
                    inputArray[x][y] = 0;
                    makeNeighbouringFlashes(x, y);
                    numOfFlashes++;
                    hasChanged = true;
                }
            }
        }
        return hasChanged;
    }

    private void makeNeighbouringFlashes(int x, int y) {
        for (int[] dir : DIRx8)  {
            int a = x + dir[0];
            int b = y + dir[1];
            if (b >= 0 && b < 10 && a >= 0 && a < 10 && inputArray[a][b] != 0) ++inputArray[a][b];
        }
    }

    private boolean isAllFlashing() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if (inputArray[x][y] != 0) return false;
            }
        }
        return true;
    }

}
