package carrot.scaleTheory.model;

import java.util.List;

public class ScalePhylum {

    private List<ScaleClass> classes;

    private String name;
    private final int octaveSize;

    public ScalePhylum(int octaveSize) {
        this.octaveSize = octaveSize;
    }

    public List<ScaleClass> getClasses() {
        return classes;
    }

    public void setClasses(List<ScaleClass> classes) {
        this.classes = classes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOctaveSize() {
        return octaveSize;
    }
}
