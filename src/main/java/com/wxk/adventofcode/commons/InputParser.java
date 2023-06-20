package com.wxk.adventofcode.commons;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.wxk.adventofcode.commons.Constants.*;

public class InputParser {

    public static int[] getInputAsIntArray(String input) {
        return Arrays.stream(input.split(WIN_EOL_DELIM)).mapToInt(Integer::parseInt).toArray();
    }

    public static long[] getInputAsLongArray(String input) {
        return Arrays.stream(input.split(WIN_EOL_DELIM)).mapToLong(Long::parseLong).toArray();
    }

    public static String[] getInputAsStringArray(String input) {
//        return Arrays.stream(input.split(WIN_EOL_DELIM)).toArray(String[]::new);
        return input.split(WIN_EOL_DELIM);
    }

    public static String[] getInputAsStringArrayDoubleEolDelimited(String input) {
        return input.split(WIN_DBL_EOL_DELIM);
    }

    public static List<String> getInputAsStringList(String input) {
        return Arrays.stream(input.split(WIN_EOL_DELIM)).collect(Collectors.toList());
    }

    public static int[][] getInputAsIntIntArray(String input) {
        String[] rowsArray = getInputAsStringArray(input);
        if (rowsArray.length > 0) {
            int[][] cellsArray = new int[rowsArray.length][rowsArray[0].length()];
            for (int y = 0; y < rowsArray.length; y++) {
                cellsArray[y] = Arrays.stream(rowsArray[y].split("")).mapToInt(Integer::parseInt).toArray();
            }
            return cellsArray;
        }
        return new int[0][0];
    }

    public static String getInputAsSingleString(String input) {
        return input.replaceAll(WIN_EOL_DELIM,"");
    }

    public static String[] getInputAsStringArrayComaDelimited(String input) {
        return input.replaceAll(WIN_EOL_DELIM,"").split(COMA_DELIM);
    }
}
