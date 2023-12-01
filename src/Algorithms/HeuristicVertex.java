package Algorithms;

import DataStructures.GraphableValue;

import java.util.ArrayList;
import java.util.List;

public class HeuristicVertex extends Vertex {
    HeuristicFunction heuristicFunction;


    public HeuristicVertex(GraphableValue value, int distanceFromRoot, HeuristicVertex priorVertex, HeuristicFunction heuristicFunction) {
        super(value, distanceFromRoot, priorVertex);
        this.heuristicFunction = heuristicFunction;
    }

    public HeuristicVertex(GraphableValue value, HeuristicFunction heuristicFunction) {
        super(value);
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
        List<GraphableValue> adjecntValues = this.getValue().getAdjecntValues();
        List<Vertex> adjecntVertices = new ArrayList<Vertex>();
        for (GraphableValue adjecntValue : adjecntValues) {
            adjecntVertices.add(new HeuristicVertex(adjecntValue, this.heuristicFunction));
        }

        return adjecntVertices;
    }

}