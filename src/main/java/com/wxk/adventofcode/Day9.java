package com.wxk.adventofcode;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Day9 extends DayResolution {

    private int[][] inputNumbers;
    private int maxX;
    private int maxY;

    public Day9() {
        super(9);
    }

    @Override
    public void makeCalculations() throws IOException {

        List<Point> listOfLowPoints = new ArrayList<>();

        inputNumbers = getInputAsIntIntArray();

        maxX = inputNumbers.length;
        maxY = inputNumbers[0].length;

        int res = 0;

        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                if (isLowPoint(x, y)) {
                    res += (inputNumbers[x][y] + 1);
                    listOfLowPoints.add(new Point(x, y));
                }
            }
        }

        firstResult = String.valueOf(res);

        List<Integer> basinSizes = new ArrayList<>();

        for (Point point : listOfLowPoints) {
            basinSizes.add(getBasinSize(point));
        }

        res= 1;
        basinSizes.sort(Comparator.reverseOrder());
        for (int i = 0; i < 3; i++) {
            res *= basinSizes.get(i);
        }

        secondResult = String.valueOf(res);
    }

    private int getBasinSize(Point point) {
        List<Point> basin = new ArrayList<>();
        basin.add(point);
        boolean increased = true;
        int tempSize = basin.size();
        while (increased) {
            int tempSize2 = basin.size();
            for (int x = 0; x < tempSize2; x++) {
                Point newPoint = basin.get(x);
                List<Point> tempBasin = checkBasin(newPoint);
                tempBasin.removeAll(basin);
                basin.addAll(tempBasin);
            }
            if (basin.size() > tempSize) {
                tempSize = basin.size();
            } else {
                increased = false;
            }
        }

        return tempSize;
    }

    private boolean isLowPoint(int x, int y) {
        int ref = inputNumbers[x][y];
        for (int[] dir : DIRx8)  {
            int a = x + dir[0];
            int b = y + dir[1];
            if (b >= 0 && b < maxY && a >= 0 && a < maxX && ref >= inputNumbers[a][b]) {
                return false;
            }
        }
        return true;
    }

    private List<Point> checkBasin(Point point) {
        List<Point> newList = new ArrayList<>();
        newList.add(point);
        int x = (int)point.getX();
        int y = (int)point.getY();
        for (int[] dir : DIRx4)  {
            int a = x + dir[0];
            int b = y + dir[1];
            if (b >= 0 && b < maxY && a >= 0 && a < maxX && inputNumbers[a][b] < 9) {
                newList.add(new Point(a,b));
            }
        }
        return newList;
    }
}
