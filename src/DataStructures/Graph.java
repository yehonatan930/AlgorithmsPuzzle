package DataStructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph<T> {
    private Map<Vertex<T>, List<Vertex<T>>> adjecntVerticesPerVertex;

    public Graph() {
        this.adjecntVerticesPerVertex = new HashMap<Vertex<T>, List<Vertex<T>>>();
    }

    public Graph(Map<Vertex<T>, List<Vertex<T>>> adjecntVerticesPerVertex) {
        this.adjecntVerticesPerVertex = adjecntVerticesPerVertex;
    }

    public Map<Vertex<T>, List<Vertex<T>>> getAdjecntVerticesPerVertex() {
        return adjecntVerticesPerVertex;
    }

    public void setAdjecntVerticesPerVertex(Map<Vertex<T>, List<Vertex<T>>> adjecntVerticesPerVertex) {
        this.adjecntVerticesPerVertex = adjecntVerticesPerVertex;
    }


    public void addVertex(Vertex<T> vertex) {
        this.adjecntVerticesPerVertex.putIfAbsent(vertex, new ArrayList<Vertex<T>>());
    }

    public void removeVertex(Vertex<T> vertex) {
        this.adjecntVerticesPerVertex.values().forEach(e -> e.remove(vertex));
        this.adjecntVerticesPerVertex.remove(vertex);
    }


    public void addEdge(Vertex<T> v1, Vertex<T> v2) {
        this.adjecntVerticesPerVertex.get(v1).add(v2);
        this.adjecntVerticesPerVertex.get(v2).add(v1);
    }


    public void removeEdge(Vertex<T> v1, Vertex<T> v2) {
        List<Vertex<T>> eV1 = this.adjecntVerticesPerVertex.get(v1);
        List<Vertex<T>> eV2 = this.adjecntVerticesPerVertex.get(v2);
        if (eV1 != null)
            eV1.remove(v2);
        if (eV2 != null)
            eV2.remove(v1);
    }


    public List<Vertex<T>> getAdjVertices(Vertex<T> vertex) {
        return adjecntVerticesPerVertex.get(vertex);
    }

}
