package com.wxk.adventofcode;

import java.io.IOException;
import java.util.*;

public class Day14 extends DayResolution {

    private String polymer;
    private final List<String> instructions = new ArrayList<>();
    private Map<String, Long> pairMap = new HashMap<>();
    private List<String> inputList = new ArrayList<>();
    char firstLetter;
    char lastLetter;

    public Day14() {
        super(14);
    }

    @Override
    public void makeCalculations() throws IOException {

        inputList = getInputAsStringList();
        polymer = inputList.get(0);
        firstLetter = polymer.charAt(0);
        lastLetter = polymer.charAt(polymer.length()-1);

        for (int i = 2; i < inputList.size(); i++) {
            instructions.add(inputList.get(i));
        }

        initializeStructures();
        for (int i = 0; i < 10; i++) {
            fillPolymer();
        }
        firstResult = String.valueOf(countElements());

        initializeStructures();
        for (int i = 0; i < 40; i++) {
            fillPolymer();
        }
        secondResult = String.valueOf(countElements());
    }

    private void initializeStructures() {
        polymer = inputList.get(0);
        char[] polymerChars = polymer.toCharArray();
        pairMap.clear();
        for (int i = 0; i < polymerChars.length-1; i++) {
            String pair = String.valueOf(polymerChars,i,2);
            pairMap.put(pair, pairMap.getOrDefault(pair, 0L)+1);
        }
    }

    private void fillPolymer() {

        Map<String, Long> newPairMap = new HashMap<>();
        for (Map.Entry<String, Long> entry : pairMap.entrySet()) {
            boolean inserted = false;
            for (String instructionLine : instructions) {
                String[] instruction = instructionLine.split("\\s->\\s");
                String polName = instruction[0];
                String insertion = instruction[1];
                if (entry.getKey().equals(polName)) {
                    inserted = true;
                    String pair1 = polName.charAt(0) + insertion;
                    String pair2 = insertion + polName.charAt(1);
                    newPairMap.put(pair1,newPairMap.getOrDefault(pair1, 0L)+entry.getValue());
                    newPairMap.put(pair2,newPairMap.getOrDefault(pair2, 0L)+entry.getValue());
                }
            }
            if (!inserted) {
                newPairMap.put(entry.getKey(),newPairMap.getOrDefault(entry.getKey(), 0L)+1);
            }
        }

        pairMap = newPairMap;
    }

    private long countElements() {
        Map<Character, Long> countMap = new HashMap<>();
        for (Map.Entry<String, Long> entry : pairMap.entrySet()) {
            char c1 = entry.getKey().charAt(0);
            char c2 = entry.getKey().charAt(1);
            countMap.put(c1, countMap.getOrDefault(c1, 0L) + entry.getValue());
            countMap.put(c2, countMap.getOrDefault(c2, 0L) + entry.getValue());
        }

        countMap.put(firstLetter,countMap.get(firstLetter)+1);
        countMap.put(lastLetter,countMap.get(lastLetter)+1);

        countMap.replaceAll((k, v) -> v / 2);

        long maxCount = 0;
        long minCount = Long.MAX_VALUE;

        for (Map.Entry<Character, Long> entry : countMap.entrySet()) {
            long count = entry.getValue();
            if (maxCount < count) {
                maxCount = count;
            }
            if (minCount > count) {
                minCount = count;
            }
        }
        return maxCount - minCount;
    }
}
