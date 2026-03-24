package carrot.scaleTheory.model;

import java.util.List;

public class ScaleFamily {

    private final ScaleOrder scaleOrder;
    private List<Scale> scales;

    private String name;
    private final int[] pattern;

    private int maxEnharmonicLvl;
    private int minEnharmonicLvl;

    public ScaleFamily(ScaleOrder scaleOrder, int[] pattern) {
        this.scaleOrder = scaleOrder;
        this.pattern = pattern;
    }

    public ScaleOrder getScaleOrder() {
        return scaleOrder;
    }

    public int getOctaveSize() {
        return scaleOrder.getOctaveSize();
    }

    public int getTonalSize() {
        return scaleOrder.getTonalSize();
    }

    public List<Scale> getScales() {
        return scales;
    }

    public void setScales(List<Scale> scales) {
        this.scales = scales;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getPattern() {
        return pattern;
    }

    public int getMaxEnharmonicLvl() {
        return maxEnharmonicLvl;
    }

    public void setMaxEnharmonicLvl(int maxEnharmonicLvl) {
        this.maxEnharmonicLvl = maxEnharmonicLvl;
    }

    public int getMinEnharmonicLvl() {
        return minEnharmonicLvl;
    }

    public void setMinEnharmonicLvl(int minEnharmonicLvl) {
        this.minEnharmonicLvl = minEnharmonicLvl;
    }
}
