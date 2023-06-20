package com.wxk.adventofcode.year2021;

import com.wxk.adventofcode.commons.InputParser;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class Day10 extends DayResolution {

    private final Map<Character,Character> matchingChars = new HashMap<>();

    public Day10() {
        super(10);
    }

    @Override
    public void makeCalculations() throws IOException {

        matchingChars.put('[',']');
        matchingChars.put('(',')');
        matchingChars.put('<','>');
        matchingChars.put('{','}');

        List<String> inputList = InputParser.getInputAsStringList(dayInput);

        long res = 0L;
        for (String line : inputList) {
            Deque<Character> returnDeq = checkChunks(line,1);
            if (returnDeq.size() == 1) {
                res = calculatePoints1(returnDeq.getFirst(),res);
            }
        }

        firstResult = String.valueOf(res);

        List<Long> resList = new LinkedList<>();
        for (String line : inputList) {
            Deque<Character> incChar = checkChunks(line, 2);
            if (!incChar.isEmpty()) {
                res = 0L;
                Iterator<Character> it = incChar.descendingIterator();
                while (it.hasNext()) {
                    res = calculatePoints2(it.next(), res);
                }
                resList.add(res);
            }
        }
        Collections.sort(resList);

        secondResult = String.valueOf(resList.get(resList.size()/2));
    }

    private long calculatePoints1(char inChar, long res) {

        switch (inChar) {
            case ')':
                res += 3;
                break;
            case ']':
                res += 57;
                break;
            case '}':
                res += 1197;
                break;
            case '>':
                res += 25137;
                break;
            default:
                break;
        }
        return res;
    }

    private long calculatePoints2(char inChar, long res) {

        switch (inChar) {
            case ')':
                res = (res*5 + 1);
                break;
            case ']':
                res = (res*5 + 2);
                break;
            case '}':
                res = (res*5 + 3);
                break;
            case '>':
                res = (res*5 + 4);
                break;
            default:
                break;
        }
        return res;
    }

    private Deque<Character> checkChunks(String line, int part) {

        Deque<Character> heapList = new ArrayDeque<>();

        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (Pattern.matches("[\\[({<]",Character.toString(ch))) {
                heapList.add(matchingChars.get(ch));
            } else {
                char last = heapList.getLast();
                if (last == ch) {
                    heapList.removeLast();
                } else {
                    Deque<Character> returnDeq = new ArrayDeque<>();
                    if (part == 1) {
                        returnDeq.add(ch);
                    }
                    return returnDeq;
                }
            }
        }
        if (part == 1) {
            heapList.clear();
        }
        return heapList;
    }
}
