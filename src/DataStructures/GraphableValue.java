package DataStructures;

import java.util.List;

public interface GraphableValue {
    List<GraphableValue> getAdjacentValues();

    int getManhattanDistanceFromIdealValue();
}
