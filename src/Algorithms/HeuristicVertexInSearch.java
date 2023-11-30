package Algorithms;

import DataStructures.GraphableValue;
import DataStructures.Vertex;

public class HeuristicVertexInSearch<T extends GraphableValue> extends VertexInSearch<T> {
    int heuristicDistanceFromRoot;

    public HeuristicVertexInSearch(VertexInSearch<T> vertexInSearch, HeuristicFunction<T> heuristicFunction) {
        super(vertexInSearch);
        this.heuristicDistanceFromRoot = heuristicFunction.calculateHeuristic(this.getValue());
    }

    public HeuristicVertexInSearch(Vertex<T> vertex) {
        super(vertex);
        this.heuristicDistanceFromRoot = 0;
    }

    public int getHeuristicDistanceFromRoot() {
        return this.heuristicDistanceFromRoot;
    }

    public void setHeuristicDistanceFromRoot(int heuristicDistanceFromRoot) {
        this.heuristicDistanceFromRoot = heuristicDistanceFromRoot;
    }

    public int getHeuristicDistanceFromRootPlusDistanceFromRoot() {
        return this.heuristicDistanceFromRoot + this.distanceFromRoot;
    }

}
