package Algorithms;

import DataStructures.COLORS;
import DataStructures.Graph;
import DataStructures.GraphableValue;
import DataStructures.Vertex;
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
        Graph graph = new Graph(rootVertex);

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

            List<Vertex> adjacentVertices = graph.buildAndGetAdjVerticesOfVertex(u);

            for (Vertex v : adjacentVertices) {
                ColoredVertex coloredV = (ColoredVertex) v;
                if (coloredV.getColor() == COLORS.WHITE) {
                    double tentativeDistance = u.getDistanceFromRoot() + EDGE_WEIGHT;

                    coloredV.setDistanceFromRoot(tentativeDistance);
                    coloredV.setPriorVertex(u);
                    coloredV.setColor(COLORS.GRAY);
                    queue.add(coloredV);
                }
            }
            u.setColor(COLORS.BLACK);
        }

        return null;
    }

    public static DataPerRun AStar(GraphableValue root, GraphableValue goal, HeuristicFunction heuristicFunction) throws IllegalArgumentException {
        long start = System.currentTimeMillis();

        HeuristicVertex rootVertex = new HeuristicVertex(root, 0, heuristicFunction);

        Graph graph = new Graph(rootVertex);

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

            List<Vertex> adjacentVertices = graph.buildAndGetAdjVerticesOfVertex(u);

            for (Vertex v : adjacentVertices) {
                double tentativeDistance = u.getDistanceFromRoot() + EDGE_WEIGHT;

                HeuristicVertex heuristicV = (HeuristicVertex) v;
                if (tentativeDistance < heuristicV.getDistanceFromRoot()) {
                    heuristicV.setDistanceFromRoot(tentativeDistance);
                    heuristicV.setPriorVertex(u);

                    if (!openSet.contains(heuristicV)) {
                        openSet.add(heuristicV);
                    }
                }
            }
        }

        return null;
    }
}
