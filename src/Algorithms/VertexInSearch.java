package Algorithms;

import DataStructures.GraphableValue;
import DataStructures.Vertex;

public class VertexInSearch<T extends GraphableValue> extends Vertex<T> {
    int distanceFromRoot;
    Vertex<T> priorVertex;

    public VertexInSearch(Vertex<T> vertex) {
        super(vertex.getValue());
        this.distanceFromRoot = 0;
        this.priorVertex = null;
    }

    public VertexInSearch(T value) {
        super(value);
        this.distanceFromRoot = 0;
        this.priorVertex = null;
    }

    public VertexInSearch(Vertex<T> vertex, int distanceFromRoot, Vertex<T> priorVertex) {
        super(vertex.getValue());
        this.distanceFromRoot = distanceFromRoot;
        this.priorVertex = priorVertex;
    }

    public VertexInSearch(T value, int distanceFromRoot, Vertex<T> priorVertex) {
        super(value);
        this.distanceFromRoot = distanceFromRoot;
        this.priorVertex = priorVertex;
    }

    public int getDistanceFromRoot() {
        return this.distanceFromRoot;
    }

    public void setDistanceFromRoot(int distanceFromRoot) {
        this.distanceFromRoot = distanceFromRoot;
    }

    public Vertex<T> getPriorVertex() {
        return this.priorVertex;
    }

    public void setPriorVertex(Vertex<T> priorVertex) {
        this.priorVertex = priorVertex;
    }

    public boolean relax(VertexInSearch<T> vertexInSearch, int weight) {
        int tentativeDistance = this.getDistanceFromRoot() + weight;

        if (tentativeDistance < vertexInSearch.getDistanceFromRoot()) {
            vertexInSearch.setDistanceFromRoot(tentativeDistance);
            vertexInSearch.setPriorVertex(this);

            return true;
        }

        return false;
    }
}
