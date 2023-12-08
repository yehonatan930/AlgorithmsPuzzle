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
    public List<Vertex> getAdjecntVertices() {
        List<GraphableValue> adjecntValues = this.getValue().getAdjacentValues();
        List<Vertex> adjecntVertices = new ArrayList<>();
        for (GraphableValue adjecntValue : adjecntValues) {
            adjecntVertices.add(new HeuristicVertex(adjecntValue, this.heuristicFunction));
        }

        return adjecntVertices;
    }

}
