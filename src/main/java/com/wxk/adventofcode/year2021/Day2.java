package com.wxk.adventofcode.year2021;

import com.wxk.adventofcode.commons.InputParser;

import java.io.IOException;
import java.util.List;

public class Day2 extends DayResolution {

    private int depth;
    private int horPosition;
    private int aim;

    public Day2() {
        super(2);
    }

    @Override
    public void makeCalculations() throws IOException {

        setInitials();
        parseInput(false);
        firstResult = String.valueOf(horPosition * depth);

        setInitials();
        parseInput(true);
        secondResult = String.valueOf(horPosition * depth);
    }

    private void setInitials() {
        depth = 0;
        horPosition = 0;
        aim = 0;
    }

    private void parseInput(boolean useAim) {
        List<String> inputList = InputParser.getInputAsStringList(dayInput);
        for (String line : inputList) {
            String[] lineSplit = line.split(" ");
            String command = lineSplit[0];
            switch (command) {
                case "forward" :
                    horPosition += Integer.parseInt(lineSplit[1]);
                    if (useAim) depth += aim * Integer.parseInt(lineSplit[1]);
                    break;
                case "down" :
                    if (useAim) {
                        aim += Integer.parseInt(lineSplit[1]);
                    } else {
                        depth += Integer.parseInt(lineSplit[1]);
                    }
                    break;
                case "up" :
                    if (useAim) {
                        aim -= Integer.parseInt(lineSplit[1]);
                    } else {
                        depth -= Integer.parseInt(lineSplit[1]);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
