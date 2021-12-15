package com.wxk.adventofcode;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Day15 extends DayResolution {

    static final int[][] DIR = { {0,1}, {1,0}, {0,-1}, {-1,0} };
    private int[][] inputArr;
    private int maxX;
    private int maxXX;
    public Day15() {
        super(15);
    }

    @Override
    public void makeCalculations() throws IOException {

        inputArr = getInputAsIntIntArray();

        maxX = inputArr.length;
        firstResult = String.valueOf(getFirstPath());

        maxXX = maxX * 5;
        secondResult = String.valueOf(getSecondPath());
    }

    private int getFirstPath() {

        LinkedList<Point> next = new LinkedList<>();
        next.add(new Point(0,0, 0));
        Set<Point> visited = new HashSet<>();

        while (!next.isEmpty()) {
            Point curPoint = next.pollFirst();
            visited.add(curPoint);
            if (curPoint.y == maxX-1 && curPoint.x == maxX-1) {
                return curPoint.cost;
            }
            for (int[] dir : DIR)  {
                int a = curPoint.x + dir[0];
                int b = curPoint.y + dir[1];
                if (b >= 0 && b < maxX && a >= 0 && a < maxX) {
                    Point p = new Point(a, b, curPoint.cost + inputArr[b][a]);
                    if (visited.contains(p) || next.contains(p)) continue;
                    next.add(p);
                }
            }
            next.sort(Comparator.comparingInt(p -> p.cost));
        }
        return -1;
    }

    private int getSecondPath() {

        LinkedList<Point> next = new LinkedList<>();
        next.add(new Point(0,0, 0));
        Set<Point> visited = new HashSet<>();

        while (!next.isEmpty()) {
            Point cur = next.pollFirst();
            visited.add(cur);
            if (cur.y == maxXX-1 && cur.x == maxXX-1) {
                return cur.cost;
            }
            for (int[] dir : DIR)  {
                int a = cur.x + dir[0];
                int b = cur.y + dir[1];

                if (b >= 0 && b < maxXX && a >= 0 && a < maxXX) {
                    Point p = new Point(a, b, getCost(a, b) + cur.cost);
                    if (visited.contains(p) || next.contains(p)) continue;
                    next.add(p);
                }
            }
            next.sort(Comparator.comparingInt(p -> p.cost));
        }
        return -1;
    }

    int getDelta(int i, int j) {
        if (i == 0 && j == 0 ) return 0;
        if (j == 0) return i;
        return 1+getDelta(i, j-1);
    }

    private int getCost(int x, int y) {
        int a = x%maxX;
        int b = y%maxX;
        int c = inputArr[b][a];

        int i = x / maxX;
        int j = y / maxX;
        int r = (c+getDelta(i,j));
        if (r > 9) r -= 9;
        return r;
    }

    static class Point implements Comparable<Point> {
        int x;
        int y;
        int cost;
        public Point(int x, int y, int c) {
            this.x = x; this.y = y; cost = c;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Point) { return ((Point) o).x == x && ((Point) o).y == y; }
            return false;
        }

        @Override
        public int hashCode() {
            return y*1234 + x;
        }

        @Override
        public int compareTo(Point o) {
            return cost - o.cost;
        }
    }
}
