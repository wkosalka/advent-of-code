package com.wxk.adventofcode.year2021;

import java.io.IOException;
import java.util.List;

public class Day20 extends DayResolution {

    private final char[] algorithm = new char[512];


    private int maxX;
    private int maxY;

    public Day20() {
        super(20);
    }

    @Override
    public void makeCalculations() throws IOException {

        List<String> inputList = getInputAsStringList();

        for (int i = 0; i < 512; i++) {
            algorithm[i] = inputList.get(0).charAt(i);
        }

        maxX = inputList.get(2).length()+200;
        maxY = inputList.size()+200;
        char[][] image = new char[maxY][maxX];


        for (int i = 2; i < inputList.size(); i++) {
            for (int z = 0; z < inputList.get(i).length(); z++) {
                image[i+100][z+100] = inputList.get(i).charAt(z);
            }
        }
        for (int i = 0; i < 2; i++) {
            image = makeEnhancements(image);
            makeEdges(image);
        }
        firstResult = String.valueOf(calculatePoints(image));
        for (int i = 0; i < 48; i++) {
            image = makeEnhancements(image);
            makeEdges(image);
        }
        secondResult = String.valueOf(calculatePoints(image));
    }

    private void makeEdges(char[][] image) {
        char ref = image[10][10];
        for (int y = 0; y < maxY; y++) {
            image[y][0] = ref;
            image[y][maxX-1] = ref;
        }
        for (int x = 0; x < maxX; x++) {
            image[0][x] = ref;
            image[maxY-1][x] = ref;
        }
    }

    private char[][] makeEnhancements(char[][] image) {

        char[][] res = new char[maxY][maxX];
        for (int y = 1; y < maxY-1; y++) {
            for (int x = 1; x < maxX-1; x++) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int[] dir : DIRx9)  {
                    if (image[y+dir[0]][x+dir[1]] == '#') {
                        stringBuilder.append('1');
                    } else {
                        stringBuilder.append('0');
                    }
                }
                int position = Integer.parseInt(stringBuilder.toString(),2);
                char val = algorithm[position];
                res[y][x] = val;
            }
        }
        return res;
    }

    private int calculatePoints(char[][] image) {
        int res = 0;
        for (int x = 2; x < maxX-3; x++) {
            for (int y = 2; y < maxY-3; y++) {
                if (image[y][x] == '#') res++;
            }
        }
        return res;
    }
}
