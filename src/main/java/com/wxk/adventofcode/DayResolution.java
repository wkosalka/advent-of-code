package com.wxk.adventofcode;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DayResolution {
    private final int day;
    private final String filePath;
    protected String firstResult;
    protected String secondResult;
    public static final String WIN_DBL_EOL_DELIM = "\r\n\r\n";
    public static final String WIN_EOL_DELIM = "\r\n";
    static final int[][] DIRx9 = { {-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,0}, {0,1}, {1,-1}, {1,0}, {1,1} };
    static final int[][] DIRx8 = { {-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1} };
    static final int[][] DIRx4 = { {0,1}, {1,0}, {0,-1}, {-1,0} };

    protected DayResolution(int day) {
        this.day = day;
        this.filePath = "input_" + day;
        this.firstResult = "";
        this.secondResult = "";
    }

    public String getInputAsString() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(this.filePath).getFile());
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

    public int[] getInputAsIntArray() throws IOException {
        return Arrays.stream(getInputAsString().split(WIN_EOL_DELIM)).mapToInt(Integer::parseInt).toArray();
    }

    public long[] getInputAsLongArray() throws IOException {
        return Arrays.stream(getInputAsString().split(WIN_EOL_DELIM)).mapToLong(Long::parseLong).toArray();
    }

    public int[][] getInputAsIntIntArray() throws IOException {
        String[] rowsArray = getInputAsStringArray();
        if (rowsArray.length > 0) {
            int[][] cellsArray = new int[rowsArray.length][rowsArray[0].length()];
            for (int y = 0; y < rowsArray.length; y++) {
                cellsArray[y] = Arrays.stream(rowsArray[y].split("")).mapToInt(Integer::parseInt).toArray();
            }
            return cellsArray;
        }
        return new int[0][0];
    }

    public String[] getInputAsStringArray() throws IOException {
        return Arrays.stream(getInputAsString().split(WIN_EOL_DELIM)).toArray(String[]::new);
    }

    public List<String> getInputAsStringList() throws IOException {
        return Arrays.stream(getInputAsString().split(WIN_EOL_DELIM)).collect(Collectors.toList());
    }

    public void getFirstResponse() {
        System.out.println("Day " + day + " first resolution is: " + firstResult);
    }

    public void getSecondResponse() {
        System.out.println("Day " + day + " second resolution is: " + secondResult);
    }

    public abstract void makeCalculations() throws IOException;
}
