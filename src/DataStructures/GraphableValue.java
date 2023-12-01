package DataStructures;

import java.util.List;

public interface GraphableValue {
    public List<GraphableValue> getAdjecntValues();

    public int getHemingDistanceFromIdealValue();

    public int getManhattanDistanceFromIdealValue();

    public boolean isIdealValue();
}
