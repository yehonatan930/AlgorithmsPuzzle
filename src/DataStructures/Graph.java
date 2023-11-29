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

    public void printGraph() {
        for (Vertex<T> vertex : this.adjecntVerticesPerVertex.keySet()) {
            System.out.println("Vertex: ");
            System.out.println(vertex.getValue().toString());
            System.out.println("Adjecnt vertices: ");
            for (Vertex<T> adjecntVertex : this.adjecntVerticesPerVertex.get(vertex)) {
                System.out.println(adjecntVertex.getValue().toString());
            }
            System.out.println();
        }
    }

    public Vertex<T> moveRandomly(int n) {
        Vertex<T> currentVertex = this.adjecntVerticesPerVertex.keySet().iterator().next(); // get first vertex
        for (int i = 0; i < n; i++) {
            List<Vertex<T>> adjecntVertices = this.adjecntVerticesPerVertex.get(currentVertex);
            int randomIndex = (int) (Math.random() * adjecntVertices.size());
            currentVertex = adjecntVertices.get(randomIndex);
        }
        return currentVertex;
    }

    public void buildGraph(Vertex<T> vertex) {
        if (vertex == null) {
            return;
        }
        T value = vertex.getValue();
        if (value == null) {
            return;
        }

        for (Vertex<T> adjecntVertex : vertex.getAdjecntVertices()) {
            if (!this.getAdjecntVerticesPerVertex().containsKey(adjecntVertex)) {
                this.addVertex(adjecntVertex);
                this.addEdge(vertex, adjecntVertex);
                buildGraph(adjecntVertex);
            }
        }
    }
}
