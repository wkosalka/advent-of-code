package com.wxk.adventofcode;

import java.io.IOException;
import java.util.*;

public class Day12 extends DayResolution {

    private final Map<Vertex, List<Vertex>> adjVertices = new HashMap<>();
    private final Stack<Vertex> connectionPath = new Stack<>();
    private final List<Stack<Vertex>> connectionPaths = new ArrayList<>();
    private static final String START_NAME = "start";
    private static final String END_NAME = "end";

    public Day12() {
        super(12);
    }

    @Override
    public void makeCalculations() throws IOException {

        List<String> inputList = getInputAsStringList();
        inputList
                .forEach(e -> {
                    String[] graph = e.split("-");
                    addVertex(graph[0]);
                    addVertex(graph[1]);
                    addEdge(graph[0],graph[1]);
                });

        Vertex v1 = new Vertex(START_NAME);
        Vertex v2 = new Vertex(END_NAME);

        findAllPaths(v1, v2, 1);
        firstResult = String.valueOf(connectionPaths.size());

        connectionPaths.clear();
        findAllPaths(v1, v2, 2);
        secondResult = String.valueOf(connectionPaths.size());
    }

    boolean isLowerCase(String text) {
        return text.toLowerCase(Locale.ROOT).equals(text);
    }

    void findAllPaths(Vertex v1, Vertex v2, int mode) {
        for (Vertex nextNode : adjVertices.get(v1)) {
            if (nextNode.equals(v2)) {
                Stack<Vertex> temp = new Stack<>();
                temp.addAll(connectionPath);
                connectionPaths.add(temp);
            } else if (isPossiblePath(nextNode,mode)) {
                connectionPath.push(nextNode);
                findAllPaths(nextNode, v2, mode);
                connectionPath.pop();
            }
        }
    }

    boolean isPossiblePath(Vertex nextNode, int mode) {
        if (nextNode.getLabel().equals(START_NAME) || nextNode.getLabel().equals(END_NAME) || mode == 1) {
            return !(Collections.frequency(connectionPath, nextNode) >= 1 && isLowerCase(nextNode.getLabel()));
        }
        return (checkNode(nextNode));
    }

    private boolean checkNode(Vertex nextNode) {
        if (!isLowerCase(nextNode.getLabel())) return true;
        Set<String> visited = new HashSet<>();
        boolean alreadyDouble = false;

        for (Vertex vertex : connectionPath) {
            String vertexName = vertex.getLabel();
            if ((isLowerCase(vertexName) && (!visited.add(vertexName)))) {
                if (alreadyDouble) {
                    return false;
                } else {
                    alreadyDouble = true;
                }
            }
        }
        return (!alreadyDouble || visited.add(nextNode.getLabel()));
    }

    void addVertex(String label) {
        if (!label.equals(END_NAME)) {
            adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
        }
    }

    void addEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        if (!(label2.equals(START_NAME) || label1.equals(END_NAME))) adjVertices.get(v1).add(v2);
        if (!(label1.equals(START_NAME) || label2.equals(END_NAME))) adjVertices.get(v2).add(v1);
    }

    static class Vertex {
        private final String label;
        Vertex(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return vertex.label.equals(this.label);
        }

        @Override
        public int hashCode() {
            return Objects.hash(label);
        }
    }
}
