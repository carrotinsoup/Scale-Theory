package carrot.scaleTheory.model;

import java.util.ArrayList;
import java.util.List;

public class ScalePhylum {

    private final List<ScaleClass> classes;

    private String name;
    private final int octaveSize;

    public ScalePhylum(int octaveSize) {
        this.octaveSize = octaveSize;
        this.classes = new ArrayList<>();
    }

    public List<ScaleClass> getClasses() {
        return classes;
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
