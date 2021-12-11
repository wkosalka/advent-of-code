package com.wxk.adventofcode;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day4 extends DayResolution {

    public Day4() {
        super(4);
    }

    @Override
    public void makeCalculations() throws IOException {

        String[] inputString = getInputAsString().split(WIN_DBL_EOL_DELIM);
        int[] drawnNumbers = Arrays.stream(inputString[0].split(",")).mapToInt(Integer::parseInt).toArray();
        List<String> boardsAsStringList = Arrays.stream(inputString, 1, inputString.length).collect(Collectors.toList());
        List<SingleBoard> boardsList = new ArrayList<>();

        for (String board : boardsAsStringList) {
            SingleBoard singleBoard = getSingleBoard(board);
            boardsList.add(singleBoard);
        }

        firstResult = getFirstResult(drawnNumbers, boardsList);
        secondResult = getSecondResult(drawnNumbers, boardsList);
    }

    private String getFirstResult(int[] drawnNumbers, List<SingleBoard> boardsList) {
        for (int drawnNumber : drawnNumbers) {
            for (SingleBoard board : boardsList) {
                boolean won = board.checkWin(drawnNumber);
                if (won) {
                    return String.valueOf(board.getBoardSum() * drawnNumber);
                }
            }
        }
        return "";
    }

    private String getSecondResult(int[] drawnNumbers, List<SingleBoard> boardsList) {

        int numOfWonBoards = 0;

        for (int drawnNumber : drawnNumbers) {
            for (SingleBoard board : boardsList) {
                boolean won = board.checkWin(drawnNumber);
                if ((won) && (!board.isWon())) {
                    board.setWon(true);
                    numOfWonBoards++;
                    if (numOfWonBoards == boardsList.size()) {
                        return String.valueOf(board.getBoardSum() * drawnNumber);

                    }
                }
            }
        }

        return "";
    }

    private SingleBoard getSingleBoard(String board) {
        String[] boardLines = board.split(WIN_EOL_DELIM);
        Map<Key, Integer> boardMap = new HashMap<>();
        for (int x = 0; x < boardLines.length; x++) {
            String[] sRow = boardLines[x].trim().replaceAll(" +", " ").split(" ");
            for (int i = 0; i < 5; i++) {
                boardMap.put(new Key(x, i), Integer.parseInt(sRow[i]));
            }
        }
        return new SingleBoard(boardMap);
    }

    class SingleBoard {
        private Map<Key, Integer> cells = new HashMap<>();
        private boolean won;

        public SingleBoard(Map<Key, Integer> rows) {
            this.cells.putAll(rows);
            won = false;
        }

        public boolean isWon() {
            return won;
        }

        public void setWon(boolean won) {
            this.won = won;
        }

        public int getBoardSum() {
            int res = 0;
            for (Map.Entry<Key, Integer> entry : cells.entrySet()) {
                if (entry.getValue() != -1) {
                    res += entry.getValue();
                }
            }
            return res;
        }

        public boolean checkWin(int drawnNumber) {
            for (Map.Entry<Key, Integer> entry : cells.entrySet()) {
                if (entry.getValue() == drawnNumber) {
                    Key key = entry.getKey();
                    cells.put(key, -1);
                    boolean colsMarked = checkCols(key.getKey2());
                    boolean rowsMarked = checkRows(key.getKey1());
                    return colsMarked || rowsMarked;
                }
            }
            return false;
        }

        private boolean checkRows(int rowIdx) {
            for (int i = 0; i < 5; i++) {
                if (cells.get(new Key(rowIdx, i)) != -1) return false;
            }
            return true;
        }

        private boolean checkCols(int colIdx) {
            for (int i = 0; i < 5; i++) {
                if (cells.get(new Key(i, colIdx)) != -1) return false;
            }
            return true;
        }
    }

    static class Key
    {
        private final int key1;
        private final int key2;

        public Key(int key1, int key2)
        {
            this.key1 = key1;
            this.key2 = key2;
        }

        public int getKey1() {
            return key1;
        }

        public int getKey2() {
            return key2;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Key key = (Key) o;
            return (key1 == key.key1) && (key2 == key.key2);
        }

        @Override
        public int hashCode()
        {
            return 31 * key1 + key2;
        }
    }
}


