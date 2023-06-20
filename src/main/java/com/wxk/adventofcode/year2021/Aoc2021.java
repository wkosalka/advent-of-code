package com.wxk.adventofcode.year2021;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Aoc2021 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {

        for (int i = 1; i <= 25; i++) {
            DayResolution dayInstance = (DayResolution) Class.forName("com.wxk.adventofcode.year2021.Day" + i).getDeclaredConstructor().newInstance();
            dayInstance.makeCalculations();
            dayInstance.getFirstResponse();
            dayInstance.getSecondResponse();
            System.out.println("-------------\n");
        }
    }
}
