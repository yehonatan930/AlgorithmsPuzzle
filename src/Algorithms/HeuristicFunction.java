package Algorithms;

import DataStructures.GraphableValue;

@FunctionalInterface
public interface HeuristicFunction {
    int calculateHeuristic(GraphableValue value);
}
