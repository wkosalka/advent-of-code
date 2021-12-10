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
    private static final String REGEXP_VAL = "\\r?\\n";

    protected DayResolution(int day) {
        this.day = day;
        this.filePath = "input_" + day;
        this.firstResult = "";
        this.secondResult = "";
    }

    private String readInputAsString() throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(this.filePath).getFile());
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

    public int[] getInputAsIntArray() throws IOException {
        return Arrays.stream(readInputAsString().split(REGEXP_VAL)).mapToInt(Integer::parseInt).toArray();
    }

    public String[] getInputAsStringArray() throws IOException {
        return (String[]) Arrays.stream(readInputAsString().split(REGEXP_VAL)).toArray();
    }

    public List<String> getInputAsStringList() throws IOException {
        return Arrays.stream(readInputAsString().split(REGEXP_VAL)).collect(Collectors.toList());
    }

    public void getFirstResponse() {
        System.out.println("Day " + day + " first resolution is: " + firstResult);
    }

    public void getSecondResponse() {
        System.out.println("Day " + day + " second resolution is: " + secondResult);
    }

    public abstract void makeCalculations() throws IOException;
}
