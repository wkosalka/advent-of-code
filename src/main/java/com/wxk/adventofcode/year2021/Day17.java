package com.wxk.adventofcode.year2021;

import java.awt.*;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day17 extends DayResolution {

    private int xVel;
    private int yVel;
    private int targetX1;
    private int targetX2;
    private int targetY2;
    private int targetY1;
    private int maxY = 0;

    public Day17() {
        super(17);
    }

    @Override
    public void makeCalculations() throws IOException {

        List<Integer> inputList = new ArrayList<>();

        Matcher m = Pattern.compile("-?\\d+").matcher(dayInput);
        while (m.find()) {
            inputList.add(Integer.parseInt(m.group()));
        }

        targetX1 = inputList.get(0);
        targetX2 = inputList.get(1);
        targetY2 = inputList.get(2);
        targetY1 = inputList.get(3);

        Set<String> setOfSpeeds = new HashSet<>();

        for (int x = 0; x <= targetX2; x++) {
            for (int y = targetY2; y < Math.abs(targetY2); y++) {
                checkVelocities(x, y, setOfSpeeds);
            }
        }

        firstResult = String.valueOf(maxY);
        secondResult = String.valueOf(setOfSpeeds.size());
    }

    private void checkVelocities(int x, int y, Set<String> setOfSpeeds) {
        xVel = x;
        yVel = y;
        Point p = new Point(0,0);
        boolean finished = false;

        while (!finished) {
            p = nexStep(p);
            if (p.getY() > maxY) maxY = (int) p.getY();
            if ((p.getX() >= targetX1 && p.getX() <= targetX2 && p.getY() >= targetY2 && p.getY() <= targetY1)) {
                setOfSpeeds.add(x+","+y);
                finished = true;
            } else if (p.getX() > targetX2 || p.getY() < targetY2) {
                finished = true;
            }
        }
    }

    private Point nexStep(Point p) {
        int x = (int) p.getX() + xVel;
        int y = (int) p.getY() + yVel;
        if (xVel > 0) xVel--;
        yVel--;
        return new Point(x,y);
    }
}
