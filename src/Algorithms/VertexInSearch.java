package Algorithms;

import DataStructures.GraphableValue;
import DataStructures.Vertex;

import java.util.ArrayList;
import java.util.List;

public class VertexInSearch extends Vertex {
    int distanceFromRoot;
    Vertex priorVertex;

    public VertexInSearch(Vertex vertex) {
        super(vertex.getValue());
        this.distanceFromRoot = 0;
        this.priorVertex = null;
    }

    public VertexInSearch(GraphableValue value) {
        super(value);
        this.distanceFromRoot = 0;
        this.priorVertex = null;
    }

    public VertexInSearch(Vertex vertex, int distanceFromRoot, Vertex priorVertex) {
        super(vertex.getValue());
        this.distanceFromRoot = distanceFromRoot;
        this.priorVertex = priorVertex;
    }

    public VertexInSearch(GraphableValue value, int distanceFromRoot, Vertex priorVertex) {
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

    public Vertex getPriorVertex() {
        return this.priorVertex;
    }

    public void setPriorVertex(Vertex priorVertex) {
        this.priorVertex = priorVertex;
    }

    public boolean relax(VertexInSearch vertexInSearch, int weight) {
        int tentativeDistance = this.getDistanceFromRoot() + weight;

        if (tentativeDistance < vertexInSearch.getDistanceFromRoot()) {
            vertexInSearch.setDistanceFromRoot(tentativeDistance);
            vertexInSearch.setPriorVertex(this);

            return true;
        }
        return false;
    }

    @Override
    public List<Vertex> getAdjecntVertices() {
        List<GraphableValue> adjecntValues = this.getValue().getAdjecntValues();
        List<Vertex> adjecntVertices = new ArrayList<Vertex>();
        for (GraphableValue adjecntValue : adjecntValues) {
            adjecntVertices.add(new VertexInSearch(adjecntValue));
        }

        return adjecntVertices;
    }
}
