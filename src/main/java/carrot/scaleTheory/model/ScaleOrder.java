package carrot.scaleTheory.model;

import java.util.List;

public class ScaleOrder {

    private final ScaleClass scaleClass;
    private List<ScaleFamily> families;

    private String name;
    private final int[] intervals;

    private int homogeneityLvl;
    private int maxEnharmonicLvl;
    private int minEnharmonicLvl;

    public ScaleOrder(ScaleClass scaleClass, int[] intervals) {
        this.scaleClass = scaleClass;
        this.intervals = intervals;
    }

    public ScaleClass getScaleClass() {
        return scaleClass;
    }

    public int getOctaveSize() {
        return scaleClass.getOctaveSize();
    }

    public int getTonalSize() {
        return scaleClass.getTonalSize();
    }

    public List<ScaleFamily> getFamilies() {
        return families;
    }

    public void setFamilies(List<ScaleFamily> families) {
        this.families = families;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getIntervals() {
        return intervals;
    }

    public int getHomogeneityLvl() {
        return homogeneityLvl;
    }

    public void setHomogeneityLvl(int homogeneityLvl) {
        this.homogeneityLvl = homogeneityLvl;
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
