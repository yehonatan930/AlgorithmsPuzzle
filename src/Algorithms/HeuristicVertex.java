package Algorithms;

import DataStructures.GraphableValue;
import DataStructures.Vertex;

import java.util.ArrayList;
import java.util.List;

public class HeuristicVertex extends Vertex {
    HeuristicFunction heuristicFunction;

    public HeuristicVertex(GraphableValue value, HeuristicFunction heuristicFunction) {
        super(value);
        this.heuristicFunction = heuristicFunction;
    }

    public HeuristicVertex(GraphableValue value, int distanceFromRoot, HeuristicFunction heuristicFunction) {
        super(value, distanceFromRoot);
        this.heuristicFunction = heuristicFunction;
    }

    public int calculateHeuristicDistanceFromRoot() {
        return this.heuristicFunction.calculateHeuristic(this.getValue());
    }

    public int getHeuristicDistanceFromRootPlusDistanceFromRoot() {
        return this.calculateHeuristicDistanceFromRoot() + this.distanceFromRoot;
    }

    @Override
    public List<Vertex> getAdjacentVertices() {
        List<GraphableValue> adjacentValues = this.getValue().getAdjacentValues();
        List<Vertex> adjacentVertices = new ArrayList<>();
        for (GraphableValue adjacentValue : adjacentValues) {
            adjacentVertices.add(new HeuristicVertex(adjacentValue, this.heuristicFunction));
        }

        return adjacentVertices;
    }

}
