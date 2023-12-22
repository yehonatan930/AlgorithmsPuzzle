package IO;

public class DataPerRun {
    private final long durationInMilliseconds;
    private final int numberOfVerticesDiscovered;
    private final int routeLength;

    public DataPerRun(long durationInMilliseconds, int numberOfVerticesDiscovered, int routeLength) {
        this.durationInMilliseconds = durationInMilliseconds;
        this.numberOfVerticesDiscovered = numberOfVerticesDiscovered;
        this.routeLength = routeLength;
    }

    public long getDurationInMilliseconds() {
        return this.durationInMilliseconds;
    }

    public int getNumberOfVerticesDiscovered() {
        return this.numberOfVerticesDiscovered;
    }

    public int getRouteLength() {
        return this.routeLength;
    }

    @Override
    public String toString() {
        return "{" +
                " durationInMilliseconds = " + durationInMilliseconds +
                ", numberOfVerticesDiscovered = " + numberOfVerticesDiscovered +
                ", routeLength = " + routeLength +
                '}';
    }
}
