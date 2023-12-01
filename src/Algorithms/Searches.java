package Algorithms;

import DataStructures.COLORS;
import DataStructures.Graph;
import DataStructures.GraphableValue;
import DataStructures.Vertex;

import java.util.*;
import java.util.stream.Collectors;

public class Searches<T extends GraphableValue> {
    public static HeuristicFunction<GraphableValue> heming = GraphableValue::getHemingDistanceFromIdealValue;
    public static HeuristicFunction<GraphableValue> manhattan = GraphableValue::getManhattanDistanceFromIdealValue;
    public static HeuristicFunction<GraphableValue> dijkstra = value -> 0;

    public ArrayList<VertexInColoredSearch<T>> BFS(Graph<T> graph, Vertex<T> root, Vertex<T> goal) {
        ArrayList<VertexInColoredSearch<T>> verticesInSearch = new ArrayList<VertexInColoredSearch<T>>();
        for (Vertex<T> v : graph.getAdjecntVerticesPerVertex().keySet()) {
            if (v.equals(root)) continue;
            verticesInSearch.add(new VertexInColoredSearch<T>(v));
        }
        VertexInColoredSearch<T> rootInSearch = new VertexInColoredSearch<T>(root, 0, null, COLORS.GRAY);
        verticesInSearch.add(rootInSearch);

        Queue<VertexInColoredSearch<T>> queue = new LinkedList<VertexInColoredSearch<T>>();
        queue.add(rootInSearch);

        while (!queue.isEmpty()) {
            VertexInColoredSearch<T> vertexInColoredSearch = queue.poll();

            if (vertexInColoredSearch.equals(goal)) {
                // Goal reached
                break;
            }

            for (Vertex<T> v : graph.getAdjVertices(vertexInColoredSearch)) {
                VertexInColoredSearch<T> adjV = verticesInSearch.get(verticesInSearch.indexOf(new VertexInColoredSearch<T>(v)));
                if (adjV.getColor() == COLORS.WHITE) {
                    adjV.setColor(COLORS.GRAY);
                    adjV.setDistanceFromRoot(vertexInColoredSearch.getDistanceFromRoot() + 1);
                    adjV.setPriorVertex(vertexInColoredSearch);
                    queue.add(adjV);
                }
            }
            vertexInColoredSearch.setColor(COLORS.BLACK);
        }

        return verticesInSearch;
    }

    public ArrayList<VertexInSearch<T>> InitializeSingleSource(Graph<T> graph, Vertex<T> root) {
        ArrayList<VertexInSearch<T>> verticesInSearch = new ArrayList<VertexInSearch<T>>();
        for (Vertex<T> v : graph.getAdjecntVerticesPerVertex().keySet()) {
            if (v.equals(root)) continue;
            verticesInSearch.add(new VertexInSearch<T>(v));
        }
        VertexInSearch<T> rootInSearch = new VertexInSearch<T>(root, 0, null);
        verticesInSearch.add(rootInSearch);

        return verticesInSearch;
    }

//    public ArrayList<VertexInSearch<T>> Dijskstra(Graph<T> graph, Vertex<T> root) {
//        ArrayList<VertexInSearch<T>> verticesInSearch = InitializeSingleSource(graph, root);
//
//        ArrayList<VertexInSearch<T>> verticesInSearchQueue = new ArrayList<VertexInSearch<T>>(verticesInSearch);
//        while (!verticesInSearchQueue.isEmpty()) {
//            VertexInSearch<T> vertexInSearch = verticesInSearchQueue.get(0);
//            for (Vertex<T> v : graph.getAdjVertices(vertexInSearch)) {
//                VertexInSearch<T> adjV = verticesInSearch.get(verticesInSearch.indexOf(new VertexInSearch<T>(v)));
//                adjV.relax(vertexInSearch, graph.getWeight(vertexInSearch, adjV));
//            }
//            verticesInSearchQueue.remove(vertexInSearch);
//        }
//
//        return verticesInSearch;
//    }

    public ArrayList<HeuristicVertexInSearch<T>> AStar(Graph<T> graph, Vertex<T> start, Vertex<T> goal, HeuristicFunction<T> heuristicFunction) {
        ArrayList<HeuristicVertexInSearch<T>> verticesInSearch = InitializeSingleSource(graph, start).stream()
                .map(vertexInSearch -> new HeuristicVertexInSearch<T>(vertexInSearch, heuristicFunction))
                .collect(Collectors.toCollection(ArrayList::new));


        PriorityQueue<HeuristicVertexInSearch<T>> openSet = new PriorityQueue<>(Comparator.comparingInt(HeuristicVertexInSearch::getHeuristicDistanceFromRootPlusDistanceFromRoot));
        openSet.addAll(verticesInSearch);

        while (!openSet.isEmpty()) {
            HeuristicVertexInSearch<T> currentVertex = openSet.poll();

            if (currentVertex.equals(goal)) {
                // Goal reached
                break;
            }

            for (Vertex<T> neighbor : graph.getAdjVertices(currentVertex)) {
                HeuristicVertexInSearch<T> neighborInSearch = verticesInSearch.get(verticesInSearch.indexOf(new HeuristicVertexInSearch<>(neighbor)));

                if (currentVertex.relax(neighborInSearch, graph.getWeight(currentVertex, neighborInSearch))) {
                    if (!openSet.contains(neighborInSearch)) {
                        openSet.add(neighborInSearch);
                    }
                }
            }
        }

        return verticesInSearch;
    }

}
