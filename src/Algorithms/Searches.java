package Algorithms;

import DataStructures.COLORS;
import DataStructures.Graph;
import DataStructures.GraphableValue;
import IO.DataPerRun;

import java.util.*;

public class Searches {
    public static HeuristicFunction heming = GraphableValue::getHemingDistanceFromIdealValue;
    public static HeuristicFunction manhattan = GraphableValue::getManhattanDistanceFromIdealValue;
    public static HeuristicFunction dijkstra = value -> 0;

    public static HeuristicFunction bad = value -> (int) (Math.random() * 10);

    public static DataPerRun BFS(GraphableValue root, GraphableValue goal) {
        long start = System.currentTimeMillis();

        Graph<ColoredVertex> graph = new Graph<ColoredVertex>(new ColoredVertex(root));

        Queue<ColoredVertex> queue = new LinkedList<ColoredVertex>();
        ColoredVertex rootVertex = new ColoredVertex(root);
        queue.add(rootVertex);

        while (!queue.isEmpty()) {
            ColoredVertex u = queue.poll();

            if (u.getValue().equals(goal)) {
                // Goal reached
                return new DataPerRun(System.currentTimeMillis() - start, graph.getAdjecntVerticesPerVertex().keySet().size(), new Route(rootVertex, u).getRouteLength());
            }

            List<ColoredVertex> adjecntVertices = graph.getAndFillAdjVerticesOfVertex(u);

            for (ColoredVertex v : adjecntVertices) {
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

        Graph<HeuristicVertex> graph = new Graph<HeuristicVertex>(new HeuristicVertex(root, heuristicFunction));

        PriorityQueue<HeuristicVertex> openSet = new PriorityQueue<>(Comparator.comparingInt(HeuristicVertex::getHeuristicDistanceFromRootPlusDistanceFromRoot));
        HeuristicVertex rootVertex = new HeuristicVertex(root, heuristicFunction);
        openSet.add(rootVertex);

        while (!openSet.isEmpty()) {
            HeuristicVertex u = openSet.poll();

            if (u.getValue().equals(goal)) {

                return new DataPerRun(System.currentTimeMillis() - start, graph.getAdjecntVerticesPerVertex().keySet().size(), new Route(rootVertex, u).getRouteLength());
            }

            List<HeuristicVertex> adjecntVertices = graph.getAndFillAdjVerticesOfVertex(u);

            for (HeuristicVertex v : adjecntVertices) {
                if (u.relax(v, graph.getWeight(u, v)) && !openSet.contains(v)) {
                    openSet.add(v);
                }
            }
        }

        return null;
    }
}
