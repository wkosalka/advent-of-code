package com.wxk.adventofcode;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day5 extends DayResolution {

    private final List<Line> lineList = new ArrayList<>();

    public Day5() {
        super(5);
    }

    @Override
    public void makeCalculations() throws IOException {

        List<String> inputList = getInputAsStringList();
        inputList.forEach(e -> {
            String[] coors = e.split(" -> |,");
            lineList.add(new Line(Integer.parseInt(coors[0]), Integer.parseInt(coors[1]), Integer.parseInt(coors[2]), Integer.parseInt(coors[3])));
        });

        firstResult = String.valueOf(getOverlappings(false));

        secondResult = String.valueOf(getOverlappings(true));
    }

    private int getOverlappings(boolean includeDiagonals) {
        Set<Point> singleCrossing = new HashSet<>();
        Set<Point> multipleCrossing = new HashSet<>();
        for (Line line : lineList) {
            if (line.isHorizontal()) {
                processHorizontal(line, singleCrossing, multipleCrossing);
            } else if (line.isVertical()) {
                processVertical(line, singleCrossing, multipleCrossing);
            } else if (includeDiagonals) {
                processDiagonal(line, singleCrossing, multipleCrossing);
            }
        }
        return multipleCrossing.size();
    }

    private void processHorizontal(Line line, Set<Point> singleCrossing, Set<Point>multipleCrossing) {
        for (int i = Math.min(line.getX1(),line.getX2()); i <= Math.max(line.getX1(),line.getX2()); i++) {
            Point p = new Point(Math.min(line.getY1(),line.getY2()), i);
            if (!singleCrossing.add(p)) multipleCrossing.add(p);
        }
    }

    private void processVertical(Line line, Set<Point> singleCrossing, Set<Point>multipleCrossing) {
        for (int i = Math.min(line.getY1(),line.getY2()); i <= Math.max(line.getY1(),line.getY2()); i++) {
            Point p = new Point(i, Math.min(line.getX1(),line.getX2()));
            if (!singleCrossing.add(p)) multipleCrossing.add(p);
        }
    }

    private void processDiagonal(Line line, Set<Point> singleCrossing, Set<Point>multipleCrossing) {
        if ((line.getX1() < line.getX2() && line.getY1() < line.getY2()) || (line.getX1() > line.getX2() && line.getY1() > line.getY2())) {
            for (int i = Math.min(line.getX1(),line.getX2()), z = Math.min(line.getY1(),line.getY2()); i <= Math.max(line.getX1(),line.getX2()); i++, z++) {
                Point p = new Point(z, i);
                if (!singleCrossing.add(p)) multipleCrossing.add(p);
            }
        } else {
            for (int i = Math.min(line.getX1(),line.getX2()), z = Math.max(line.getY1(),line.getY2()); i <= Math.max(line.getX1(),line.getX2()); i++, z--) {
                Point p = new Point(z, i);
                if (!singleCrossing.add(p)) multipleCrossing.add(p);
            }
        }
    }

    class Line {
        private final int x1;
        private final int x2;
        private final int y1;
        private final int y2;

        public Line(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }

        public int getX1() {
            return x1;
        }

        public int getX2() {
            return x2;
        }

        public int getY1() {
            return y1;
        }

        public int getY2() {
            return y2;
        }

        public boolean isVertical() {
            return x1 == x2;
        }

        public boolean isHorizontal() {
            return y1 == y2;
        }
    }
}


