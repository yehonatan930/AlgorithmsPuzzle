package Algorithms;

import DataStructures.COLORS;
import DataStructures.Graph;
import DataStructures.GraphableValue;
import IO.DataPerRun;

import java.util.*;

public class Searches {
    public static final HeuristicFunction manhattan = GraphableValue::getManhattanDistanceFromIdealValue;
    public static final HeuristicFunction dijkstra = value -> 0;
    public static final HeuristicFunction bad = value -> value.getManhattanDistanceFromIdealValue() + (Math.random() * 10);
    private static final int EDGE_WEIGHT = 1;

    public static DataPerRun BFS(GraphableValue root, GraphableValue goal) throws IllegalArgumentException {
        long start = System.currentTimeMillis();

        ColoredVertex rootVertex = new ColoredVertex(root, 0, COLORS.GRAY);
        Graph<ColoredVertex> graph = new Graph<>(rootVertex);

        Queue<ColoredVertex> queue = new LinkedList<>();
        queue.add(rootVertex);

        while (!queue.isEmpty()) {
            ColoredVertex u = queue.poll();

            if (u.getValue().equals(goal)) {
                // Goal reached

                long elapsedTime = System.currentTimeMillis() - start;
                int numberOfVertices = graph.getAdjacentVerticesPerVertex().keySet().size();
                int routeLength = rootVertex.getRouteLengthTo(u);

                return new DataPerRun(elapsedTime, numberOfVertices, routeLength);
            }

            List<ColoredVertex> adjacentVertices = graph.buildAndGetAdjVerticesOfVertex(u);

            for (ColoredVertex v : adjacentVertices) {
                if (v.getColor() == COLORS.WHITE) {
                    double tentativeDistance = u.getDistanceFromRoot() + EDGE_WEIGHT;

                    v.setDistanceFromRoot(tentativeDistance);
                    v.setPriorVertex(u);
                    v.setColor(COLORS.GRAY);
                    queue.add(v);
                }
            }
            u.setColor(COLORS.BLACK);
        }

        return null;
    }

    public static DataPerRun AStar(GraphableValue root, GraphableValue goal, HeuristicFunction heuristicFunction) throws IllegalArgumentException {
        long start = System.currentTimeMillis();

        HeuristicVertex rootVertex = new HeuristicVertex(root, 0, heuristicFunction);

        Graph<HeuristicVertex> graph = new Graph<>(rootVertex);

        PriorityQueue<HeuristicVertex> openSet = new PriorityQueue<>(
                Comparator.comparingDouble(HeuristicVertex::getHeuristicDistanceFromRootPlusDistanceFromRoot));
        openSet.add(rootVertex);

        while (!openSet.isEmpty()) {
            HeuristicVertex u = openSet.poll();

            if (u.getValue().equals(goal)) {
                long elapsedTime = System.currentTimeMillis() - start;
                int numberOfVertices = graph.getAdjacentVerticesPerVertex().keySet().size();
                int routeLength = rootVertex.getRouteLengthTo(u);

                return new DataPerRun(elapsedTime, numberOfVertices, routeLength);
            }

            List<HeuristicVertex> adjacentVertices = graph.buildAndGetAdjVerticesOfVertex(u);

            for (HeuristicVertex v : adjacentVertices) {
                double tentativeDistance = u.getDistanceFromRoot() + EDGE_WEIGHT;

                if (tentativeDistance < v.getDistanceFromRoot()) {
                    v.setDistanceFromRoot(tentativeDistance);
                    v.setPriorVertex(u);

                    if (!openSet.contains(v)) {
                        openSet.add(v);
                    }
                }
            }
        }

        return null;
    }
}
