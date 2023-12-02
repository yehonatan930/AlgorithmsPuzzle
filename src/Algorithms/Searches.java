package Algorithms;

import DataStructures.COLORS;
import DataStructures.Graph;
import DataStructures.GraphableValue;

import java.util.*;

public class Searches {
    public static HeuristicFunction heming = GraphableValue::getHemingDistanceFromIdealValue;
    public static HeuristicFunction manhattan = GraphableValue::getManhattanDistanceFromIdealValue;
    public static HeuristicFunction dijkstra = value -> 0;

    public static HeuristicFunction bad = value -> (int) (Math.random() * 10);

    public static Graph<ColoredVertex> BFS(GraphableValue root, GraphableValue goal) {
        Graph<ColoredVertex> graph = new Graph<ColoredVertex>(new ColoredVertex(root));

        Queue<ColoredVertex> queue = new LinkedList<ColoredVertex>();
        ColoredVertex rootVertex = new ColoredVertex(root);
        queue.add(rootVertex);

        while (!queue.isEmpty()) {
            ColoredVertex coloredVertex = queue.poll();

            if (coloredVertex.getValue().equals(goal)) {
                // Goal reached
                break;
            }

            List<ColoredVertex> adjecntVertices = graph.getAndFillAdjVerticesOfVertex(coloredVertex);

            for (ColoredVertex v : adjecntVertices) {
                if (v.getColor() == COLORS.WHITE) {
                    v.setColor(COLORS.GRAY);
                    v.setDistanceFromRoot(coloredVertex.getDistanceFromRoot() + 1);
                    v.setPriorVertex(coloredVertex);
                    queue.add(v);
                }
            }
            coloredVertex.setColor(COLORS.BLACK);
        }

        return graph;
    }

    public static Graph<HeuristicVertex> AStar(GraphableValue root, GraphableValue goal, HeuristicFunction heuristicFunction) {
        Graph<HeuristicVertex> graph = new Graph<HeuristicVertex>(new HeuristicVertex(root, heuristicFunction));

        PriorityQueue<HeuristicVertex> openSet = new PriorityQueue<>(Comparator.comparingInt(HeuristicVertex::getHeuristicDistanceFromRootPlusDistanceFromRoot));
        HeuristicVertex rootVertex = new HeuristicVertex(root, heuristicFunction);
        openSet.add(rootVertex);

        while (!openSet.isEmpty()) {
            HeuristicVertex currentVertex = openSet.poll();

            if (currentVertex.getValue().equals(goal)) {
                // Goal reached
                break;
            }

            List<HeuristicVertex> adjecntVertices = graph.getAndFillAdjVerticesOfVertex(currentVertex);

            for (HeuristicVertex neighbor : adjecntVertices) {
                if (currentVertex.relax(neighbor, graph.getWeight(currentVertex, neighbor))) {
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return graph;
    }
}
