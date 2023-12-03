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

    @Override
    public String toString() {
        return "DataPerRun{" +
                "durationInMilliseconds=" + durationInMilliseconds +
                ", numberOfVerticesDiscovered=" + numberOfVerticesDiscovered +
                ", routeLength=" + routeLength +
                '}';
    }
}
