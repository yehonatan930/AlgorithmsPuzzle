package Algorithms;

import DataStructures.COLORS;
import DataStructures.GraphableValue;
import DataStructures.Vertex;

import java.util.ArrayList;
import java.util.List;

public class ColoredVertex extends Vertex {
    COLORS color;

    public ColoredVertex(GraphableValue value) {
        super(value);
        this.color = COLORS.WHITE;
    }

    public ColoredVertex(GraphableValue value, int distanceFromRoot, COLORS color) {
        super(value, distanceFromRoot);
        this.color = color;
    }

    public COLORS getColor() {
        return this.color;
    }

    public void setColor(COLORS color) {
        this.color = color;
    }

    @Override
    public List<Vertex> getAdjacentVertices() {
        List<GraphableValue> adjacentValues = this.getValue().getAdjacentValues();
        List<Vertex> adjacentVertices = new ArrayList<>();
        for (GraphableValue adjacentValue : adjacentValues) {
            adjacentVertices.add(new ColoredVertex(adjacentValue));
        }

        return adjacentVertices;
    }
}
