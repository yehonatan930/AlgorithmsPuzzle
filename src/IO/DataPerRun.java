package IO;

public class DataPerRun {
    long durationInMilliseconds;
    int numberOfVerticesDiscovered;
    int routeLength;

    public DataPerRun(long durationInMilliseconds, int numberOfVerticesDiscovered, int routeLength) {
        this.durationInMilliseconds = durationInMilliseconds;
        this.numberOfVerticesDiscovered = numberOfVerticesDiscovered;
        this.routeLength = routeLength;
    }

}
