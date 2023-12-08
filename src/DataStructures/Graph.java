package DataStructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Graph<V extends Vertex> {
    private Map<V, List<V>> adjecntVerticesPerVertex;

    public Graph() {
        this.adjecntVerticesPerVertex = new HashMap<V, List<V>>();
    }

    public Graph(V vertex) {
        this.adjecntVerticesPerVertex = new HashMap<V, List<V>>();
        this.addV(vertex);
    }

    public Graph(Map<V, List<V>> adjecntVerticesPerVertex) {
        this.adjecntVerticesPerVertex = adjecntVerticesPerVertex;
    }

    public Map<V, List<V>> getAdjecntVerticesPerVertex() {
        return adjecntVerticesPerVertex;
    }

    public void setAdjecntVerticesPerVertex(Map<V, List<V>> adjecntVerticesPerVertex) {
        this.adjecntVerticesPerVertex = adjecntVerticesPerVertex;
    }


    public void addV(V vertex) {
        this.adjecntVerticesPerVertex.putIfAbsent(vertex, new ArrayList<V>());
    }

    public void removeV(V vertex) {
        this.adjecntVerticesPerVertex.values().forEach(e -> e.remove(vertex));
        this.adjecntVerticesPerVertex.remove(vertex);
    }


    public void addEdge(V v1, V v2) {
        this.adjecntVerticesPerVertex.get(v1).add(v2);
        this.adjecntVerticesPerVertex.get(v2).add(v1);
    }


    public void removeEdge(V v1, V v2) {
        List<V> eV1 = this.adjecntVerticesPerVertex.get(v1);
        List<V> eV2 = this.adjecntVerticesPerVertex.get(v2);
        if (eV1 != null)
            eV1.remove(v2);
        if (eV2 != null)
            eV2.remove(v1);
    }


    public List<V> getAdjVerticesOfVertex(V vertex) {
        return this.adjecntVerticesPerVertex.get(vertex);
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

        List<V> adjecntVertices = vertex.getAdjacentVertices().stream().map(v -> (V) v).collect(Collectors.toList());

        for (V adjecntVertex : adjecntVertices) {
            boolean isVertexAlreadyInGraph = this.getAdjecntVerticesPerVertex().containsKey(adjecntVertex);

            if (!isVertexAlreadyInGraph) {
                this.addV(adjecntVertex);
                this.addEdge(vertex, adjecntVertex);
            }
        }
    }

    public int getWeight(V v1, V v2) {
        return 1;
    }
}
