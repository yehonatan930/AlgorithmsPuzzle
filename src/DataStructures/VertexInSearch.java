package DataStructures;

public class VertexInSearch<T> {
    Vertex<T> vertex;
    int distanceFromRoot;
    Vertex<T> priorVertex;
    COLORS color;

    public VertexInSearch(Vertex<T> vertex) {
        this.vertex = vertex;
        this.distanceFromRoot = 0;
        this.priorVertex = null;
        this.color = COLORS.WHITE;
    }

    public VertexInSearch(Vertex<T> vertex, int distanceFromRoot, Vertex<T> priorVertex, COLORS color) {
        this.vertex = vertex;
        this.distanceFromRoot = distanceFromRoot;
        this.priorVertex = priorVertex;
        this.color = color;
    }

    public Vertex<T> getVertex() {
        return vertex;
    }

    public void setVertex(Vertex<T> vertex) {
        this.vertex = vertex;
    }

    public int getDistanceFromRoot() {
        return distanceFromRoot;
    }

    public void setDistanceFromRoot(int distanceFromRoot) {
        this.distanceFromRoot = distanceFromRoot;
    }

    public Vertex<T> getPriorVertex() {
        return priorVertex;
    }

    public void setPriorVertex(Vertex<T> priorVertex) {
        this.priorVertex = priorVertex;
    }

    public COLORS getColor() {
        return color;
    }

    public void setColor(COLORS color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VertexInSearch)) return false;

        VertexInSearch<?> that = (VertexInSearch<?>) o;

        return getVertex().equals(that.getVertex());
    }

    @Override
    public int hashCode() {
        return getVertex().hashCode();
    }
}
