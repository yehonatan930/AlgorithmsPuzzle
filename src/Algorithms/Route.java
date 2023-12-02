package Algorithms;

import DataStructures.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Route {
    Vertex root;
    Vertex goal;

    public Route(Vertex root, Vertex goal) {
        this.root = root;
        this.goal = goal;
    }

    public List<Vertex> getRoute() {
        List<Vertex> route = new ArrayList<Vertex>();
        Vertex current = this.goal;
        while (current != null) {
            route.add(current);
            current = current.getPriorVertex();
        }
        Collections.reverse(route);
        return route;
    }

    public int getRouteLength() {
        return this.getRoute().size();
    }
}
