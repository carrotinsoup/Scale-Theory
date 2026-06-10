package carrot.scaleTheory.calculatingFunctions;

import java.util.*;

public class EnharmonicFunction {

    private final int degree;

    private final Map<int[], DegreeFunction> enharmonicFunctions;

    public EnharmonicFunction(int degree) {
        this.degree = degree;
        this.enharmonicFunctions = new HashMap<>();
    }

    public int getDegree() {
        return degree;
    }

    public void addFunction(int[] mediants, DegreeFunction function) {
        enharmonicFunctions.put(mediants, function);
    }

    public Map<int[], DegreeFunction> getFunctions() {
        return enharmonicFunctions;
    }
}
