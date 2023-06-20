package com.wxk.adventofcode.year2021;

import com.wxk.adventofcode.commons.InputParser;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Day25 extends DayResolution {
    public Day25() {
        super(25);
    }

    private int maxX;
    private int maxY;

    private final Map<Character, Point> moveMap = new HashMap<>();

    @Override
    public void makeCalculations() throws IOException {

        moveMap.put('>', new Point(1,0));
        moveMap.put('v', new Point(0,1));

        List<String> inputList = InputParser.getInputAsStringList(dayInput);

        Map<Point, Character> cucumberMap = new HashMap<>();
        maxX = inputList.get(0).length();
        maxY = inputList.size();

        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                if (inputList.get(y).charAt(x) != '.') {
                    cucumberMap.put(new Point(x, y), inputList.get(y).charAt(x));
                }
            }
        }
        int count = 0;
        boolean finished = false;
        while (!finished) {
            Map<Point, Character> tmpCucumberMap;
            tmpCucumberMap = processMove(cucumberMap, '>');
            tmpCucumberMap = processMove(tmpCucumberMap, 'v');
            finished = tmpCucumberMap.keySet().equals(cucumberMap.keySet());
            count++;
            cucumberMap = tmpCucumberMap;
        }

        firstResult = String.valueOf(count);
        secondResult = "Surprise!";
    }

    private Map<Point, Character> processMove(Map<Point, Character> cucumberMap, char moveChar) {
        Map<Point, Character> resMap = new HashMap<>();
        for (Map.Entry<Point, Character> entry : cucumberMap.entrySet()) {
            if (entry.getValue() == moveChar) {
                Point p = entry.getKey();
                Point movePoint = moveMap.get(moveChar);
                int newX = (int) (p.getX() + movePoint.getX());
                if (newX >= maxX) newX = 0;
                int newY = (int) (p.getY() + movePoint.getY());
                if (newY >= maxY) newY = 0;
                Point nextPoint = new Point(newX, newY);
                if (cucumberMap.containsKey(nextPoint)) {
                    resMap.put(p, moveChar);
                } else {
                    resMap.put(nextPoint, moveChar);
                }
            } else {
                resMap.put(entry.getKey(), entry.getValue());
            }
        }
        return resMap;
    }

}
