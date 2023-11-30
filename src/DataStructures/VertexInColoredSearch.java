package DataStructures;

public class VertexInColoredSearch<T extends GraphableValue> extends VertexInSearch<T> {
    COLORS color;

    public VertexInColoredSearch(Vertex<T> vertex) {
        super(vertex);
        this.color = COLORS.WHITE;
    }

    public VertexInColoredSearch(T value) {
        super(value);
        this.color = COLORS.WHITE;
    }

    public VertexInColoredSearch(Vertex<T> vertex, int distanceFromRoot, Vertex<T> priorVertex, COLORS color) {
        super(vertex, distanceFromRoot, priorVertex);
        this.color = color;
    }

    public VertexInColoredSearch(T value, int distanceFromRoot, Vertex<T> priorVertex, COLORS color) {
        super(value, distanceFromRoot, priorVertex);
        this.color = color;
    }

    public COLORS getColor() {
        return this.color;
    }

    public void setColor(COLORS color) {
        this.color = color;
    }
}
