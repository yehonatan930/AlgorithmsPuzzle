package DataStructures;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    GraphableValue value;

    public Vertex(GraphableValue value) {
        this.value = value;
    }

    public GraphableValue getValue() {
        return value;
    }

    public void setValue(GraphableValue value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;

        Vertex vertex = (Vertex) o;

        return this.getValue() != null ? this.getValue().equals(vertex.getValue()) : vertex.getValue() == null;
    }

    @Override
    public int hashCode() {
        return getValue() != null ? getValue().hashCode() : 0;
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
