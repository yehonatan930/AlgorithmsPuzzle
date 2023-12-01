package Algorithms;

import DataStructures.COLORS;
import DataStructures.Graph;
import DataStructures.GraphableValue;

import java.util.*;

public class Searches {
    public static HeuristicFunction heming = GraphableValue::getHemingDistanceFromIdealValue;
    public static HeuristicFunction manhattan = GraphableValue::getManhattanDistanceFromIdealValue;
    public static HeuristicFunction dijkstra = value -> 0;

    public static Graph<ColoredVertex> BFS(GraphableValue root, GraphableValue goal) {
        Graph<ColoredVertex> graph = new Graph<ColoredVertex>(new ColoredVertex(root));

        Queue<ColoredVertex> queue = new LinkedList<ColoredVertex>();
        queue.add(new ColoredVertex(root));

        while (!queue.isEmpty()) {
            ColoredVertex coloredVertex = queue.poll();

            if (coloredVertex.getValue().equals(goal)) {
                // Goal reached
                break;
            }

            List<ColoredVertex> adjecntVertices = graph.getAndFillAdjVertices(coloredVertex);

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

    public static Graph<HeuristicVertex> AStar(GraphableValue start, GraphableValue goal, HeuristicFunction heuristicFunction) {
        Graph<HeuristicVertex> graph = new Graph<HeuristicVertex>(new HeuristicVertex(start, heuristicFunction));

        PriorityQueue<HeuristicVertex> openSet = new PriorityQueue<>(Comparator.comparingInt(HeuristicVertex::getHeuristicDistanceFromRootPlusDistanceFromRoot));
        openSet.add(new HeuristicVertex(start, heuristicFunction));

        while (!openSet.isEmpty()) {
            HeuristicVertex currentVertex = openSet.poll();

            if (currentVertex.getValue().equals(goal)) {
                // Goal reached
                break;
            }

            List<HeuristicVertex> adjecntVertices = graph.getAndFillAdjVertices(currentVertex);

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
