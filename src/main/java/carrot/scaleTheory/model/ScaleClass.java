package carrot.scaleTheory.model;

import java.util.ArrayList;
import java.util.List;

public class ScaleClass {

    private final ScalePhylum scalePhylum;
    private final List<ScaleOrder> orders;

    private String name;
    private final int tonalSize;

    public ScaleClass(ScalePhylum scalePhylum, int tonalSize) {
        this.scalePhylum = scalePhylum;
        this.tonalSize = tonalSize;
        this.orders = new ArrayList<>();
    }

    public ScalePhylum getScalePhylum() {
        return scalePhylum;
    }

    public int getOctaveSize() {
        return scalePhylum.getOctaveSize();
    }

    public List<ScaleOrder> getOrders() {
        return orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTonalSize() {
        return tonalSize;
    }
}
