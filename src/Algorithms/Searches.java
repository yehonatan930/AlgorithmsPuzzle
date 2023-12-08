package Algorithms;

import DataStructures.COLORS;
import DataStructures.Graph;
import DataStructures.GraphableValue;
import IO.DataPerRun;

import java.util.*;

public class Searches {
    public static HeuristicFunction manhattan = GraphableValue::getManhattanDistanceFromIdealValue;
    public static HeuristicFunction dijkstra = value -> 0;

    public static HeuristicFunction bad = value -> (int) (Math.random() * 10);

    public static DataPerRun BFS(GraphableValue root, GraphableValue goal) {
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

            List<ColoredVertex> adjacentVertices = graph.getAndFillAdjVerticesOfVertex(u);

            for (ColoredVertex v : adjacentVertices) {
                if (v.getColor() == COLORS.WHITE) {
                    v.setColor(COLORS.GRAY);
                    v.setDistanceFromRoot(u.getDistanceFromRoot() + 1);
                    v.setPriorVertex(u);
                    queue.add(v);
                }
            }
            u.setColor(COLORS.BLACK);
        }

        return null;
    }

    public static DataPerRun AStar(GraphableValue root, GraphableValue goal, HeuristicFunction heuristicFunction) {
        long start = System.currentTimeMillis();

        HeuristicVertex rootVertex = new HeuristicVertex(root, 0, heuristicFunction);

        Graph<HeuristicVertex> graph = new Graph<>(rootVertex);

        PriorityQueue<HeuristicVertex> openSet = new PriorityQueue<>(
                Comparator.comparingInt(HeuristicVertex::getHeuristicDistanceFromRootPlusDistanceFromRoot));
        openSet.add(rootVertex);

        while (!openSet.isEmpty()) {
            HeuristicVertex u = openSet.poll();

            if (u.getValue().equals(goal)) {
                long elapsedTime = System.currentTimeMillis() - start;
                int numberOfVertices = graph.getAdjacentVerticesPerVertex().keySet().size();
                int routeLength = rootVertex.getRouteLengthTo(u);

                return new DataPerRun(elapsedTime, numberOfVertices, routeLength);
            }

            List<HeuristicVertex> adjacentVertices = graph.getAndFillAdjVerticesOfVertex(u);

            for (HeuristicVertex v : adjacentVertices) {
                if (u.relax(v, graph.getWeight(u, v)) && !openSet.contains(v)) {
                    openSet.add(v);
                }
            }
        }

        return null;
    }
}
