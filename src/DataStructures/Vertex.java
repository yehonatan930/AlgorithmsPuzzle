package DataStructures;

import java.util.List;

public class Vertex<T> {
    T value;

    public Vertex(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;

        Vertex<?> vertex = (Vertex<?>) o;

        return this.getValue() != null ? this.getValue().equals(vertex.getValue()) : vertex.getValue() == null;
    }

    @Override
    public int hashCode() {
        return getValue() != null ? getValue().hashCode() : 0;
    }

    public List<Vertex<T>> getAdjecntVertices() {
        return null;
    }
}
