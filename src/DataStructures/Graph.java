package DataStructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<Vertex, List<Vertex>> adjacentVerticesPerVertex;

    public Graph(Vertex vertex) {
        this.adjacentVerticesPerVertex = new HashMap<>();
        this.addVertex(vertex);
    }

    public Map<Vertex, List<Vertex>> getAdjacentVerticesPerVertex() {
        return adjacentVerticesPerVertex;
    }

    public void setAdjacentVerticesPerVertex(Map<Vertex, List<Vertex>> adjacentVerticesPerVertex) {
        this.adjacentVerticesPerVertex = adjacentVerticesPerVertex;
    }

    public void addVertex(Vertex vertex) {
        this.adjacentVerticesPerVertex.putIfAbsent(vertex, new ArrayList<>());
    }

    public void removeVertex(Vertex vertex) {
        this.adjacentVerticesPerVertex.values().forEach(e -> e.remove(vertex));
        this.adjacentVerticesPerVertex.remove(vertex);
    }

    public void addEdge(Vertex v1, Vertex v2) {
        this.adjacentVerticesPerVertex.get(v1).add(v2);
        this.adjacentVerticesPerVertex.get(v2).add(v1);
    }

    public void removeEdge(Vertex v1, Vertex v2) {
        List<Vertex> eV1 = this.adjacentVerticesPerVertex.get(v1);
        List<Vertex> eV2 = this.adjacentVerticesPerVertex.get(v2);
        if (eV1 != null)
            eV1.remove(v2);
        if (eV2 != null)
            eV2.remove(v1);
    }

    public List<Vertex> getAdjVerticesOfVertex(Vertex vertex) {
        return this.adjacentVerticesPerVertex.get(vertex);
    }

    public List<Vertex> buildAndGetAdjVerticesOfVertex(Vertex vertex) {
        this.buildGraphAroundVertex(vertex);

        return this.getAdjVerticesOfVertex(vertex);
    }

    public void buildGraphAroundVertex(Vertex vertex) {
        if (vertex == null || vertex.getValue() == null) {
            return;
        }

        List<Vertex> adjacentVertices = vertex.getAdjacentVertices();

        for (Vertex adjacentVertex : adjacentVertices) {
            boolean isVertexAlreadyInGraph = this.getAdjacentVerticesPerVertex().containsKey(adjacentVertex);

            if (!isVertexAlreadyInGraph) {
                this.addVertex(adjacentVertex);
                this.addEdge(vertex, adjacentVertex);
            }
        }
    }
}
