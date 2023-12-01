package Algorithms;

import DataStructures.GraphableValue;
import DataStructures.Vertex;

import java.util.ArrayList;
import java.util.List;

public class HeuristicVertexInSearch extends VertexInSearch {
    HeuristicFunction heuristicFunction;


    public HeuristicVertexInSearch(GraphableValue value, int distanceFromRoot, HeuristicVertexInSearch priorVertex, HeuristicFunction heuristicFunction) {
        super(value, distanceFromRoot, priorVertex);
        this.heuristicFunction = heuristicFunction;
    }

    public HeuristicVertexInSearch(GraphableValue value, HeuristicFunction heuristicFunction) {
        super(value);
        this.heuristicFunction = heuristicFunction;
    }

    public HeuristicVertexInSearch(Vertex vertex, int distanceFromRoot, Vertex priorVertex, HeuristicFunction heuristicFunction) {
        super(vertex, distanceFromRoot, priorVertex);
        this.heuristicFunction = heuristicFunction;
    }

    public HeuristicVertexInSearch(VertexInSearch vertexInSearch, HeuristicFunction heuristicFunction) {
        super(vertexInSearch);
        this.heuristicFunction = heuristicFunction;
    }

    public HeuristicVertexInSearch(Vertex vertex, HeuristicFunction heuristicFunction) {
        super(vertex);
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
            adjecntVertices.add(new HeuristicVertexInSearch(adjecntValue, this.heuristicFunction));
        }

        return adjecntVertices;
    }

}
