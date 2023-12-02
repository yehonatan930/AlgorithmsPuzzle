package Algorithms;

public class DataPerRun {
    long durationInMilliseconds;
    int numberOfVerticesDiscovered;
    Route route;

    public DataPerRun(long durationInMilliseconds, int numberOfVerticesDiscovered, Route route) {
        this.durationInMilliseconds = durationInMilliseconds;
        this.numberOfVerticesDiscovered = numberOfVerticesDiscovered;
        this.route = route;
    }

}
