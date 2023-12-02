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
            adjecntVertices.add(new ColoredVertex(adjecntValue));
        }

        return adjecntVertices;
    }
}
