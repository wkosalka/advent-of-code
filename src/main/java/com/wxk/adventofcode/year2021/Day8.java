package com.wxk.adventofcode.year2021;

import com.wxk.adventofcode.commons.InputParser;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day8 extends DayResolution {

    private final List<Segments> segmentsList = new ArrayList<>();

    public Day8() {
        super(8);
    }

    @Override
    public void makeCalculations() throws IOException {

        List<String> inputList = InputParser.getInputAsStringList(dayInput);

        inputList.forEach(e -> {
            String[] firstSplit = e.split("\\s\\|\\s");
            String[] inputs = firstSplit[0].split("\\s");
            String[] outputs = firstSplit[1].split("\\s");
            segmentsList.add(new Segments(inputs, outputs));
        });

        long count = segmentsList.stream()
                .flatMap(e -> Arrays.stream(e.outputs))
                .filter(e -> (e.length() == 2 || e.length() == 3 || e.length() == 4 || e.length() == 7))
                .count();

        firstResult = String.valueOf(count);

        int sum = 0;
        for (Segments segments : segmentsList) {
            sum += deductAndSumNumbers(segments.inputs, segments.outputs);
        }

        secondResult = String.valueOf(sum);
    }

    private int deductAndSumNumbers(String[] inputs, String[] outputs) {

        /* Map used to map inputs to actual segments, where segments are as follows:
          aaaa
         b    c
         b    c
          dddd
         e    f
         e    f
          gggg
         */
        Map<Character, Character> mappingMap = new HashMap<>();
        Map<Character, Integer> countingMap = new HashMap<>();

        Set<Character> oneD = new HashSet<>();
        Set<Character> fourD = new HashSet<>();
        Set<Character> sevenD = new HashSet<>();

        /* digit 1 has only 2 segments
           digit 7 has only 3 segments
           digit 4 has only 4 segments
           we also count how many times each segment occurs, since this gives also some hints.
         */
        for (String digit : inputs) {
            int digLength = digit.length();
            switch (digLength) {
                case 2:
                    oneD = digit.chars().mapToObj(e->(char)e).collect(Collectors.toSet());
                    break;
                case 3:
                    sevenD = digit.chars().mapToObj(e->(char)e).collect(Collectors.toSet());
                    break;
                case 4:
                    fourD = digit.chars().mapToObj(e->(char)e).collect(Collectors.toSet());
                    break;
                default:
                    break;
            }
            for (int i = 0; i < digLength; i++) {
                char chr = digit.charAt(i);
                countingMap.put(chr, countingMap.getOrDefault(chr,0) + 1);
            }
        }

        Set<Character> segA = new HashSet<>(sevenD);
        segA.removeAll(oneD);
        char tmpCharA = segA.stream().findFirst().orElse('-');
        mappingMap.put(tmpCharA,'a');

        /* segment 'e' occurs 4 times (0, 2, 6, 8)
           segment 'b' occurs 6 times (0, 4, 5, 6, 8, 9)
           segment 'f' occurs 9 times (all but 7)
           additionally segment 'c' and 'a' occur 8 times each and since we already know input for 'a', the other one is 'c'
         */
        for (Map.Entry<Character,Integer> entry : countingMap.entrySet()) {
            int counts = countingMap.get(entry.getKey());
            switch (counts) {
                case 4:
                    mappingMap.put(entry.getKey(),'e');
                    break;
                case 6:
                    mappingMap.put(entry.getKey(),'b');
                    break;
                case 9:
                    mappingMap.put(entry.getKey(),'f');
                    break;
                case 8:
                    if (entry.getKey() != tmpCharA) mappingMap.put(entry.getKey(),'c');
                    break;
                default:
                    break;
            }
        }

        Set<Character> refSet = new HashSet<>(Arrays.asList('a', 'b', 'c','d','e','f','g'));

        /* we should have now only two unmapped signals for segments 'd' and 'g', we check which are these */
        refSet.removeAll(mappingMap.keySet());
        Set<Character> refSet2 = new HashSet<>(refSet);

        /* the 'g' segment does not occur in digit 4 */
        refSet.removeAll(fourD);
        char tmpCharG = refSet.stream().findFirst().orElse('-');
        mappingMap.put(tmpCharG,'g');

        /* now we have only one left, this must be 'd' */
        refSet2.remove(tmpCharG);
        mappingMap.put(refSet2.stream().findFirst().orElse('-'),'d');

        StringBuilder digitsBuilder = new StringBuilder();
        for (String digit : outputs) {
            digitsBuilder.append(decodeDigit(mappingMap,digit));
        }
        return Integer.parseInt(digitsBuilder.toString());
    }
    
    private String decodeDigit(Map<Character, Character> mappingMap, String digit) {
        
        StringBuilder newDigitBuilder = new StringBuilder();
        for (int i = 0; i < digit.length(); i++) {
            newDigitBuilder.append(mappingMap.get(digit.charAt(i)));
        }
        String sortedDigits = sortString(newDigitBuilder.toString());

        switch (sortedDigits) {
            case "cf" :
                return "1";
            case "acf" :
                return "7";
            case "bcdf" :
                return "4";
            case "acdeg" :
                return "2";
            case "acdfg" :
                return "3";
            case "abdfg" :
                return "5";
            case "abcefg" :
                return "0";
            case "abdefg" :
                return "6";
            case "abcdfg" :
                return "9";
            case "abcdefg" :
                return "8";
            default : //should not happen
                return "";
        }
    }

    private String sortString(String inputString)
    {
        char[] tempArray = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    private static class Segments {
        public final String[] inputs;
        public final String[] outputs;

        public Segments(String[] inputs, String[] outputs) {
            this.inputs = inputs;
            this.outputs = outputs;
        }
    }
}

