package Algorithms;

import DataStructures.GraphableValue;

@FunctionalInterface
public interface HeuristicFunction<T extends GraphableValue> {
    int calculateHeuristic(T value);
}
