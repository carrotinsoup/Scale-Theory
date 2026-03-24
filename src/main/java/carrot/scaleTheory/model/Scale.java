package carrot.scaleTheory.model;

public class Scale {

    private final ScaleFamily scaleFamily;

    private String name;
    private final int[] degrees;

    private int enharmonicLvl;
    private int brightnessLvl;
    private int brightnessVal;
    private int tonicCirclePos;

    public Scale(ScaleFamily scaleFamily, int[] degrees) {
        this.scaleFamily = scaleFamily;
        this.degrees = degrees;
    }

    public ScaleFamily getScaleFamily() {
        return scaleFamily;
    }

    public int getOctaveSize() {
        return scaleFamily.getOctaveSize();
    }

    public int getTonalSize() {
        return scaleFamily.getTonalSize();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getDegrees() {
        return degrees;
    }

    public int getDegree(int index) {
        return degrees[index];
    }

    public int getEnharmonicLvl() {
        return enharmonicLvl;
    }

    public void setEnharmonicLvl(int enharmonicLvl) {
        this.enharmonicLvl = enharmonicLvl;
    }

    public int getBrightnessLvl() {
        return brightnessLvl;
    }

    public void setBrightnessLvl(int brightnessLvl) {
        this.brightnessLvl = brightnessLvl;
    }

    public int getBrightnessVal() {
        return brightnessVal;
    }

    public void setBrightnessVal(int brightnessVal) {
        this.brightnessVal = brightnessVal;
    }

    public int getTonicCirclePos() {
        return tonicCirclePos;
    }

    public void setTonicCirclePos(int tonicCirclePos) {
        this.tonicCirclePos = tonicCirclePos;
    }
}
