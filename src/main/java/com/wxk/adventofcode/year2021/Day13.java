package com.wxk.adventofcode.year2021;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Day13 extends DayResolution {

    private final Deque<String> instructionList = new ArrayDeque<>();
    private Set<Point> pointsSet = new HashSet<>();
    private int maxX = 0;
    private int maxY = 0;

    public Day13() {
        super(13);
    }

    @Override
    public void makeCalculations() throws IOException {
        List<String> input = getInputAsStringList();

        input.forEach(e -> {
            if (e.length() > 0) {
                if (e.charAt(0) == 'f') {
                    String[] line = e.split(" ");
                    String[] line2 = line[2].split("=");
                    instructionList.add(line[2].charAt(0)+","+line2[1]);
                } else {
                    String[] split = e.split(",");
                    pointsSet.add(new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
                }
            }
        });

        String instruction = instructionList.removeFirst();
        fold(instruction);
        firstResult = String.valueOf(pointsSet.size());

        while (!instructionList.isEmpty()) {
            instruction = instructionList.removeFirst();
            fold(instruction);
        }

        char[][] paper = getPaper();
        secondResult = displayPaper(paper);
    }

    private char[][] getPaper() {

        char[][] tmp = new char[maxY][maxX];
        pointsSet.forEach(e -> tmp[(int)e.getY()][(int)e.getX()] = 'X');
        return tmp;

    }

    private void fold(String instruction) {
        String[] line = instruction.split(",");
        int foldLine = Integer.parseInt(line[1]);
        char foldDirection = line[0].charAt(0);
        if (foldDirection == 'y') {
            maxY = foldLine;
        } else {
            maxX = foldLine;
        }
        Set<Point> tempSet = new HashSet<>();

        for (Point p : pointsSet) {
            if (foldDirection == 'y') {
                if (p.getY() > foldLine) {
                    tempSet.add(new Point((int)p.getX(), 2 * foldLine - (int)p.getY()));
                } else {
                    tempSet.add(p);
                }
            } else {
                if (p.getX() > foldLine) {
                    tempSet.add(new Point(2 * foldLine - (int)p.getX(), (int)p.getY()));
                } else {
                    tempSet.add(p);
                }
            }
        }
        pointsSet = tempSet;
    }


    private String displayPaper(char[][] inputChar) {
        StringBuilder res = new StringBuilder();
        res.append(WIN_EOL_DELIM);
        for (int y = 0; y < inputChar.length; y++) {
            for (int x = 0; x < inputChar[0].length; x++) {
                char toDisplay = inputChar[y][x] > 0 ? 'X' : ' ';
                res.append(toDisplay);
                if ((x+1)%5 == 0) res.append("  ");
            }
            if (y != inputChar.length - 1) res.append(WIN_EOL_DELIM);
        }
        return res.toString();
    }

}
