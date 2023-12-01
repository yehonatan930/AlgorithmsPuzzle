package DataStructures;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    protected GraphableValue value;
    protected int distanceFromRoot;
    protected Vertex priorVertex;


    public Vertex(GraphableValue value) {
        this.value = value;
        this.distanceFromRoot = 0;
        this.priorVertex = null;
    }

    public Vertex(GraphableValue value, int distanceFromRoot, Vertex priorVertex) {
        this.value = value;
        this.distanceFromRoot = distanceFromRoot;
        this.priorVertex = priorVertex;
    }

    public GraphableValue getValue() {
        return value;
    }

    public void setValue(GraphableValue value) {
        this.value = value;
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

    public boolean relax(Vertex vertex, int weight) {
        int tentativeDistance = this.getDistanceFromRoot() + weight;

        if (tentativeDistance < vertex.getDistanceFromRoot()) {
            vertex.setDistanceFromRoot(tentativeDistance);
            vertex.setPriorVertex(this);

            return true;
        }
        return false;
    }

    public List<Vertex> getAdjecntVertices() {
        List<GraphableValue> adjecntValues = this.getValue().getAdjecntValues();
        List<Vertex> adjecntVertices = new ArrayList<Vertex>();
        for (GraphableValue adjecntValue : adjecntValues) {
            adjecntVertices.add(new Vertex(adjecntValue));
        }
        return adjecntVertices;
    }
}
