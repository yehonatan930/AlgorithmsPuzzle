package Algorithms;

import DataStructures.COLORS;
import DataStructures.Graph;
import DataStructures.Vertex;
import DataStructures.VertexInSearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Searches<T> {
    public ArrayList<VertexInSearch<T>> BFS(Graph<T> graph, Vertex<T> root) {
        ArrayList<VertexInSearch<T>> verticesInSearch = new ArrayList<VertexInSearch<T>>();
        for (Vertex<T> v : graph.getAdjecntVerticesPerVertex().keySet()) {
            if (v.equals(root)) continue;
            verticesInSearch.add(new VertexInSearch<T>(v));
        }
        VertexInSearch<T> rootInSearch = new VertexInSearch<T>(root, 0, null, COLORS.GRAY);
        verticesInSearch.add(rootInSearch);

        Queue<VertexInSearch<T>> queue = new LinkedList<VertexInSearch<T>>();
        queue.add(rootInSearch);

        while (!queue.isEmpty()) {
            VertexInSearch<T> vertexInSearch = queue.poll();
            for (Vertex<T> v : graph.getAdjVertices(vertexInSearch.getVertex())) {
                VertexInSearch<T> adjV = verticesInSearch.get(verticesInSearch.indexOf(new VertexInSearch<T>(v)));
                if (adjV.getColor() == COLORS.WHITE) {
                    adjV.setColor(COLORS.GRAY);
                    adjV.setDistanceFromRoot(vertexInSearch.getDistanceFromRoot() + 1);
                    adjV.setPriorVertex(vertexInSearch.getVertex());
                    queue.add(adjV);
                }
            }
            vertexInSearch.setColor(COLORS.BLACK);
        }

        return verticesInSearch;
    }


}
