package DataStructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Graph<V extends Vertex> {
    private Map<V, List<V>> adjacentVerticesPerVertex;

    public Graph() {
        this.adjacentVerticesPerVertex = new HashMap<V, List<V>>();
    }

    public Graph(V vertex) {
        this.adjacentVerticesPerVertex = new HashMap<V, List<V>>();
        this.addV(vertex);
    }

    public Graph(Map<V, List<V>> adjacentVerticesPerVertex) {
        this.adjacentVerticesPerVertex = adjacentVerticesPerVertex;
    }

    public Map<V, List<V>> getAdjacentVerticesPerVertex() {
        return adjacentVerticesPerVertex;
    }

    public void setAdjacentVerticesPerVertex(Map<V, List<V>> adjacentVerticesPerVertex) {
        this.adjacentVerticesPerVertex = adjacentVerticesPerVertex;
    }

    public void addV(V vertex) {
        this.adjacentVerticesPerVertex.putIfAbsent(vertex, new ArrayList<V>());
    }

    public void removeV(V vertex) {
        this.adjacentVerticesPerVertex.values().forEach(e -> e.remove(vertex));
        this.adjacentVerticesPerVertex.remove(vertex);
    }

    public void addEdge(V v1, V v2) {
        this.adjacentVerticesPerVertex.get(v1).add(v2);
        this.adjacentVerticesPerVertex.get(v2).add(v1);
    }

    public void removeEdge(V v1, V v2) {
        List<V> eV1 = this.adjacentVerticesPerVertex.get(v1);
        List<V> eV2 = this.adjacentVerticesPerVertex.get(v2);
        if (eV1 != null)
            eV1.remove(v2);
        if (eV2 != null)
            eV2.remove(v1);
    }

    public List<V> getAdjVerticesOfVertex(V vertex) {
        return this.adjacentVerticesPerVertex.get(vertex);
    }

    public List<V> getAndFillAdjVerticesOfVertex(V vertex) {
        this.buildGraphAroundVertex(vertex);

        return this.getAdjVerticesOfVertex(vertex);
    }

    public void buildGraphAroundVertex(V vertex) {
        if (vertex == null) {
            return;
        }
        GraphableValue value = vertex.getValue();
        if (value == null) {
            return;
        }

        List<V> adjacentVertices = vertex.getAdjacentVertices().stream().map(v -> (V) v).collect(Collectors.toList());

        for (V adjacentVertex : adjacentVertices) {
            boolean isVertexAlreadyInGraph = this.getAdjacentVerticesPerVertex().containsKey(adjacentVertex);

            if (!isVertexAlreadyInGraph) {
                this.addV(adjacentVertex);
                this.addEdge(vertex, adjacentVertex);
            }
        }
    }

    public int getWeight(V v1, V v2) {
        return 1;
    }
}
