package carrot.scaleTheory.model;

import java.util.List;

public class ScaleClass {

    private final ScalePhylum scalePhylum;
    private List<ScaleOrder> orders;

    private String name;
    private final int tonalSize;

    public ScaleClass(ScalePhylum scalePhylum, int tonalSize) {
        this.scalePhylum = scalePhylum;
        this.tonalSize = tonalSize;
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

    public void setOrders(List<ScaleOrder> orders) {
        this.orders = orders;
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
