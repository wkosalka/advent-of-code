package com.wxk.adventofcode.year2022;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public abstract class DayResolution {
    private final int day;
    private final String filePath;
    protected String dayInput;
    String firstResult;
    String secondResult;
    static final String WIN_DBL_EOL_DELIM = "\r\n\r\n";
    static final String WIN_EOL_DELIM = "\r\n";
    static final int[][] DIRx9 = { {-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,0}, {0,1}, {1,-1}, {1,0}, {1,1} };
    static final int[][] DIRx8 = { {-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1} };
    static final int[][] DIRx4 = { {0,1}, {1,0}, {0,-1}, {-1,0} };

    protected DayResolution(int day) {
        this.day = day;
        this.filePath = "year2022/input_" + day;
        this.firstResult = "";
        this.secondResult = "";
        readInputFile();
    }

    private void readInputFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(this.filePath).getFile());
        try {
            this.dayInput = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Could not read input file: " + e.getMessage());
        }
    }

    public void getFirstResponse() {
        System.out.println("Day " + day + " first resolution is: " + firstResult);
    }

    public void getSecondResponse() {
        System.out.println("Day " + day + " second resolution is: " + secondResult);
    }

    public abstract void makeCalculations() throws IOException;
}
