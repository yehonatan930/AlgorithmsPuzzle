package Algorithms;

import DataStructures.COLORS;
import DataStructures.Graph;
import DataStructures.GraphableValue;

import java.util.*;

public class Searches {
    public static HeuristicFunction heming = GraphableValue::getHemingDistanceFromIdealValue;
    public static HeuristicFunction manhattan = GraphableValue::getManhattanDistanceFromIdealValue;
    public static HeuristicFunction dijkstra = value -> 0;

    public static Graph<VertexInColoredSearch> BFS(GraphableValue root, GraphableValue goal) {
        Graph<VertexInColoredSearch> graph = new Graph<VertexInColoredSearch>(new VertexInColoredSearch(root));

        Queue<VertexInColoredSearch> queue = new LinkedList<VertexInColoredSearch>();
        queue.add(new VertexInColoredSearch(root));

        while (!queue.isEmpty()) {
            VertexInColoredSearch vertexInColoredSearch = queue.poll();

            if (vertexInColoredSearch.getValue().equals(goal)) {
                // Goal reached
                break;
            }

            List<VertexInColoredSearch> adjecntVertices = graph.getAndFillAdjVertices(vertexInColoredSearch);

            for (VertexInColoredSearch v : adjecntVertices) {
                if (v.getColor() == COLORS.WHITE) {
                    v.setColor(COLORS.GRAY);
                    v.setDistanceFromRoot(vertexInColoredSearch.getDistanceFromRoot() + 1);
                    v.setPriorVertex(vertexInColoredSearch);
                    queue.add(v);
                }
            }
            vertexInColoredSearch.setColor(COLORS.BLACK);
        }

        return graph;
    }

    public static Graph<HeuristicVertexInSearch> AStar(GraphableValue start, GraphableValue goal, HeuristicFunction heuristicFunction) {
        Graph<HeuristicVertexInSearch> graph = new Graph<HeuristicVertexInSearch>(new HeuristicVertexInSearch(start, heuristicFunction));

        PriorityQueue<HeuristicVertexInSearch> openSet = new PriorityQueue<>(Comparator.comparingInt(HeuristicVertexInSearch::getHeuristicDistanceFromRootPlusDistanceFromRoot));
        openSet.add(new HeuristicVertexInSearch(start, heuristicFunction));

        while (!openSet.isEmpty()) {
            HeuristicVertexInSearch currentVertex = openSet.poll();

            if (currentVertex.getValue().equals(goal)) {
                // Goal reached
                break;
            }

            List<HeuristicVertexInSearch> adjecntVertices = graph.getAndFillAdjVertices(currentVertex);

            for (HeuristicVertexInSearch neighbor : adjecntVertices) {
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
