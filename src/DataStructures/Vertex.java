package DataStructures;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    protected GraphableValue value;
    protected double distanceFromRoot;
    protected Vertex priorVertex;

    public Vertex(GraphableValue value) {
        this.value = value;
        this.distanceFromRoot = Double.POSITIVE_INFINITY;
        this.priorVertex = null;
    }

    public Vertex(GraphableValue value, double distanceFromRoot) {
        this.value = value;
        this.distanceFromRoot = distanceFromRoot;
        this.priorVertex = null;
    }

    public GraphableValue getValue() {
        return value;
    }

    public void setValue(GraphableValue value) {
        this.value = value;
    }

    public double getDistanceFromRoot() {
        return this.distanceFromRoot;
    }

    public void setDistanceFromRoot(double distanceFromRoot) {
        this.distanceFromRoot = distanceFromRoot;
    }

    public Vertex getPriorVertex() {
        return this.priorVertex;
    }

    public void setPriorVertex(Vertex priorVertex) {
        this.priorVertex = priorVertex;
    }
    
    public List<Vertex> getAdjacentVertices() {
        List<GraphableValue> adjacentValues = this.getValue().getAdjacentValues();
        List<Vertex> adjacentVertices = new ArrayList<>();
        for (GraphableValue adjacentValue : adjacentValues) {
            adjacentVertices.add(new Vertex(adjacentValue));
        }
        return adjacentVertices;
    }

    public int getRouteLengthTo(Vertex goal) throws IllegalArgumentException {
        Vertex current = goal;
        int routeLength = 0;

        while (current != null && !current.equals(this)) {
            routeLength++;
            current = current.getPriorVertex();
        }

        if (current == null) {
            throw new IllegalArgumentException("No route from goal to this vertex");
        }

        return routeLength + 1;
    }
}
