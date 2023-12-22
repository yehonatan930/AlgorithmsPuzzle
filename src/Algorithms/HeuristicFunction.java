package Algorithms;

import DataStructures.GraphableValue;

@FunctionalInterface
public interface HeuristicFunction {
    double calculateHeuristic(GraphableValue value);
}
