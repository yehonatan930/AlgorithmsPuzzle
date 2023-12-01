package Algorithms;

import DataStructures.COLORS;
import DataStructures.GraphableValue;
import DataStructures.Vertex;

import java.util.ArrayList;
import java.util.List;

public class VertexInColoredSearch extends VertexInSearch {
    COLORS color;

    public VertexInColoredSearch(Vertex vertex) {
        super(vertex);
        this.color = COLORS.WHITE;
    }

    public VertexInColoredSearch(GraphableValue value) {
        super(value);
        this.color = COLORS.WHITE;
    }

    public VertexInColoredSearch(Vertex vertex, int distanceFromRoot, Vertex priorVertex, COLORS color) {
        super(vertex, distanceFromRoot, priorVertex);
        this.color = color;
    }

    public VertexInColoredSearch(GraphableValue value, int distanceFromRoot, Vertex priorVertex, COLORS color) {
        super(value, distanceFromRoot, priorVertex);
        this.color = color;
    }

    public COLORS getColor() {
        return this.color;
    }

    public void setColor(COLORS color) {
        this.color = color;
    }

    @Override
    public List<Vertex> getAdjecntVertices() {
        List<GraphableValue> adjecntValues = this.getValue().getAdjecntValues();
        List<Vertex> adjecntVertices = new ArrayList<Vertex>();
        for (GraphableValue adjecntValue : adjecntValues) {
            adjecntVertices.add(new VertexInColoredSearch(adjecntValue));
        }

        return adjecntVertices;
    }
}
