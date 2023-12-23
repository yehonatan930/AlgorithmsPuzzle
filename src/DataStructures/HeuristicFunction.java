package DataStructures;

@FunctionalInterface
public interface HeuristicFunction {
    double calculateHeuristic(GraphableValue value);
}
