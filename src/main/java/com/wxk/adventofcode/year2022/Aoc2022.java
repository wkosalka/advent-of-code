package com.wxk.adventofcode.year2022;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Aoc2022 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {

        for (int i = 1; i <= 1; i++) {
            com.wxk.adventofcode.year2022.DayResolution dayInstance = (DayResolution) Class.forName("com.wxk.adventofcode.year2022.Day" + i).getDeclaredConstructor().newInstance();
            dayInstance.makeCalculations();
            dayInstance.getFirstResponse();
            dayInstance.getSecondResponse();
            System.out.println("-------------\n");
        }
    }
}
