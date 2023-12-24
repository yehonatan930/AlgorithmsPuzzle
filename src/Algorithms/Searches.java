package Algorithms;

import DataStructures.*;
import IO.DataPerRun;

import java.util.*;

public class Searches {
    public static final HeuristicFunction manhattan = GraphableValue::getManhattanDistanceFromIdealValue;
    public static final HeuristicFunction dijkstra = value -> 0;
    public static final HeuristicFunction unAddmissable = value -> value.getManhattanDistanceFromIdealValue() + (Math.random() * 10);
    private static final int EDGE_WEIGHT = 1;

    public static DataPerRun BFS(GraphableValue root, GraphableValue goal) throws IllegalArgumentException {
        long start = System.currentTimeMillis();

        ColoredVertex rootVertex = new ColoredVertex(root, 0, COLORS.GRAY);
        Graph graph = new Graph(rootVertex);

        Queue<ColoredVertex> unExaminedVertices = new LinkedList<>();
        unExaminedVertices.add(rootVertex);

        while (!unExaminedVertices.isEmpty()) {
            ColoredVertex examinedVertex = unExaminedVertices.poll();

            if (examinedVertex.getValue().equals(goal)) {
                long elapsedTime = System.currentTimeMillis() - start;
                int numberOfVertices = graph.getAdjacentVerticesPerVertex().keySet().size();
                int routeLength = rootVertex.getRouteLengthTo(examinedVertex);

                return new DataPerRun(elapsedTime, numberOfVertices, routeLength);
            }

            List<Vertex> adjacentVertices = graph.buildAndGetAdjVerticesOfVertex(examinedVertex);

            for (Vertex v : adjacentVertices) {
                ColoredVertex currAdjecntVertex = (ColoredVertex) v;

                if (currAdjecntVertex.getColor() == COLORS.WHITE) {
                    double tentativeDistance = examinedVertex.getDistanceFromRoot() + EDGE_WEIGHT;

                    currAdjecntVertex.setDistanceFromRoot(tentativeDistance);
                    currAdjecntVertex.setPriorVertex(examinedVertex);
                    currAdjecntVertex.setColor(COLORS.GRAY);
                    unExaminedVertices.add(currAdjecntVertex);
                }
            }
            examinedVertex.setColor(COLORS.BLACK);
        }

        return null;
    }

    public static DataPerRun AStar(GraphableValue root, GraphableValue goal, HeuristicFunction heuristicFunction) throws IllegalArgumentException {
        long start = System.currentTimeMillis();

        HeuristicVertex rootVertex = new HeuristicVertex(root, 0, heuristicFunction);

        Graph graph = new Graph(rootVertex);

        PriorityQueue<HeuristicVertex> unExaminedVertices = new PriorityQueue<>(
                Comparator.comparingDouble(HeuristicVertex::getHeuristicDistanceFromRootPlusDistanceFromRoot));
        unExaminedVertices.add(rootVertex);

        while (!unExaminedVertices.isEmpty()) {
            HeuristicVertex examinedVertex = unExaminedVertices.poll();

            if (examinedVertex.getValue().equals(goal)) {
                long elapsedTime = System.currentTimeMillis() - start;
                int numberOfVertices = graph.getAdjacentVerticesPerVertex().keySet().size();
                int routeLength = rootVertex.getRouteLengthTo(examinedVertex);

                return new DataPerRun(elapsedTime, numberOfVertices, routeLength);
            }

            List<Vertex> adjacentVertices = graph.buildAndGetAdjVerticesOfVertex(examinedVertex);

            for (Vertex v : adjacentVertices) {
                HeuristicVertex currAdjecantVertex = (HeuristicVertex) v;

                double distanceFromRootThroughExaminedVertexToCurrAdjacent = examinedVertex.getDistanceFromRoot() + EDGE_WEIGHT;
                if (distanceFromRootThroughExaminedVertexToCurrAdjacent < currAdjecantVertex.getDistanceFromRoot()) {
                    currAdjecantVertex.setDistanceFromRoot(distanceFromRootThroughExaminedVertexToCurrAdjacent);
                    currAdjecantVertex.setPriorVertex(examinedVertex);

                    if (!unExaminedVertices.contains(currAdjecantVertex)) {
                        unExaminedVertices.add(currAdjecantVertex);
                    }
                }
            }
        }

        return null;
    }
}
