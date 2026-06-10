package carrot.scaleTheory.calculatingFunctions;

import carrot.scaleTheory.model.Scale;

public class FunctionData {

    private final Scale scale;

    private DegreeFunction[] pureFunctions;
    private DegreeFunction[] adjacentFunctions;
    private DegreeFunction[] reversedFunctions;
    private EnharmonicFunction[] mediantFunctions;

    public FunctionData(Scale scale) {
        this.scale = scale;
    }

    public Scale getScale() {
        return scale;
    }

    public DegreeFunction[] getPureFunctions() {
        return pureFunctions;
    }

    public void setPureFunctions(DegreeFunction[] pureFunctions) {
        this.pureFunctions = pureFunctions;
    }

    public DegreeFunction[] getAdjacentFunctions() {
        return adjacentFunctions;
    }

    public void setAdjacentFunctions(DegreeFunction[] adjacentFunctions) {
        this.adjacentFunctions = adjacentFunctions;
    }

    public DegreeFunction[] getReversedFunctions() {
        return reversedFunctions;
    }

    public void setReversedFunctions(DegreeFunction[] reversedFunctions) {
        this.reversedFunctions = reversedFunctions;
    }

    public EnharmonicFunction[] getMediantFunctions() {
        return mediantFunctions;
    }

    public void setMediantFunctions(EnharmonicFunction[] mediantFunctions) {
        this.mediantFunctions = mediantFunctions;
    }
}
