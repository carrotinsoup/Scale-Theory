package carrot.scaleTheory.calculatingFunctions;

import carrot.scaleTheory.generalUtil.GeneralUtil12TET;
import carrot.scaleTheory.model.*;

import java.util.*;

public class FunctionsCalculator12TET {

    public static final double DEFAULT_EQUAL_TRITONES_MULTIPLIER = 0.5;
    public static final double DEFAULT_ADJACENT_FUNCTION_MULTIPLIER = 0.25;
    public static final double DEFAULT_REVERSED_FUNCTION_MULTIPLIER = 0.25;
    public static final double DEFAULT_MEDIANT_FUNCTION_MULTIPLIER = 0.5;

    public static final int[] DEFAULT_MEDIANTS_ABOVE = new int[] {3, 4};
    public static final int[] DEFAULT_MEDIANTS_BELOW = new int[] {3, 4};

    public final double equalTritonesMultiplier;
    public final double adjacentFunctionMultiplier;
    public final double reversedFunctionMultiplier;
    public final double mediantFunctionMultiplier;

    public final int[] mediantsAbove;
    public final int[] mediantsBelow;

    public FunctionsCalculator12TET() {
        this.equalTritonesMultiplier = DEFAULT_EQUAL_TRITONES_MULTIPLIER;
        this.adjacentFunctionMultiplier = DEFAULT_ADJACENT_FUNCTION_MULTIPLIER;
        this.reversedFunctionMultiplier = DEFAULT_REVERSED_FUNCTION_MULTIPLIER;
        this.mediantFunctionMultiplier = DEFAULT_MEDIANT_FUNCTION_MULTIPLIER;
        this.mediantsAbove = DEFAULT_MEDIANTS_ABOVE;
        this.mediantsBelow = DEFAULT_MEDIANTS_BELOW;
    }

    public FunctionsCalculator12TET(double equalTritonesMultiplier, double adjacentFunctionMultiplier,
                                    double reversedFunctionMultiplier, double mediantFunctionMultiplier,
                                    int[] mediantsAbove, int[] mediantsBelow) {
        this.equalTritonesMultiplier = equalTritonesMultiplier;
        this.adjacentFunctionMultiplier = adjacentFunctionMultiplier;
        this.reversedFunctionMultiplier = reversedFunctionMultiplier;
        this.mediantFunctionMultiplier = mediantFunctionMultiplier;
        this.mediantsAbove = mediantsAbove;
        this.mediantsBelow = mediantsBelow;
    }

    public FunctionData calculateFunctions(Scale scale) {
        FunctionData scaleFunctions = new FunctionData(scale);
        calculatePureFunctions(scaleFunctions);
        calculateAdjacentFunctions(scaleFunctions);
        calculateReversedFunctions(scaleFunctions);
        calculateMediantFunctions(scaleFunctions);
        return scaleFunctions;
    }

    private void calculatePureFunctions(FunctionData functionData) {
        functionData.setPureFunctions(calculatePureFunctions(functionData.getScale().getDegrees()));
    }

    private void calculateAdjacentFunctions(FunctionData functionData) {
        functionData.setAdjacentFunctions(calculateAdjacentFunctions(functionData.getScale().getDegrees()));
    }

    private void calculateReversedFunctions(FunctionData functionData) {
        functionData.setReversedFunctions(calculateReversedFunctions(functionData.getScale().getDegrees()));
    }

    private void calculateMediantFunctions(FunctionData functionData) {
        functionData.setMediantFunctions(calculateMediantFunctions(functionData.getPureFunctions()));
    }

    private DegreeFunction[] calculatePureFunctions(int[] degrees) {
        DegreeFunction[] functions = new DegreeFunction[degrees.length];
        for (int i = 0; i < degrees.length; i++) {
            functions[i] = new DegreeFunction(degrees[i]);
        }

        functions[0].setAsTonicFunction(1);

        List<int[]> tritones = new ArrayList<>();
        int[] tempDegrees = degrees.clone();
        for (int i = 0; i < tempDegrees.length; i++) {
            for (int j = 0; j < tempDegrees.length; j++) {
                if (Math.abs(tempDegrees[i] - tempDegrees[j]) == 6) {
                    tritones.add(new int[] {tempDegrees[i], tempDegrees[j]});
                    tempDegrees[i] = 0;
                    tempDegrees[j] = 0;
                }
            }
        }

        for (int[] tritone : tritones) {
            boolean equalTritones = false;
            if (GeneralUtil12TET.fourthFifthAbsIndex(tritone[0]) > GeneralUtil12TET.fourthFifthAbsIndex(tritone[1])) {
                int temp = tritone[0];
                tritone[0] = tritone[1];
                tritone[1] = temp;
            }
            else {
                if (GeneralUtil12TET.fourthFifthAbsIndex(tritone[0]) == GeneralUtil12TET.fourthFifthAbsIndex(tritone[1])) {
                    equalTritones = true;
                }
            }
            if (equalTritones) {
                for (DegreeFunction function : functions) {
                    if (function.getDegree() == tritone[0] || function.getDegree() == tritone[1]) {
                        function.setAsDominantFunction(equalTritonesMultiplier);
                        function.setAsSubdominantFunction(equalTritonesMultiplier);
                    }
                }
            }
            else {
                for (DegreeFunction function : functions) {
                    if (function.getDegree() == tritone[0]) {
                        function.setAsSubdominantFunction(1);
                    }
                    else if (function.getDegree() == tritone[1]) {
                        function.setAsDominantFunction(1);
                    }
                }
            }
        }
        return functions;
    }

    private DegreeFunction[] calculateAdjacentFunctions(int[] degrees) {
        DegreeFunction[] functions = new DegreeFunction[degrees.length];
        for (int i = 0; i < degrees.length; i++) {
            functions[i] = new DegreeFunction(degrees[i]);
        }

        for (int degree : degrees) {
            if (degree == 5 || degree == 7) {
                int[] shiftedDegrees = degrees.clone();
                GeneralUtil12TET.shiftScaleByInterval(shiftedDegrees, degree);
                DegreeFunction[] shiftedFunctions = calculatePureFunctions(shiftedDegrees);
                for (DegreeFunction function : functions) {
                    for (DegreeFunction adjacentFunction : shiftedFunctions) {
                        if (function.getDegree() == adjacentFunction.getDegree()) {
                            function.addOtherFunction(adjacentFunction, adjacentFunctionMultiplier);
                        }
                    }
                }
            }
        }
        return functions;
    }

    private DegreeFunction[] calculateReversedFunctions(int[] degrees) {
        DegreeFunction[] functions = new DegreeFunction[degrees.length];
        for (int i = 0; i < degrees.length; i++) {
            functions[i] = new DegreeFunction(degrees[i]);
        }

        for (int degree : degrees) {
            int[] shiftedDegrees = degrees.clone();
            GeneralUtil12TET.shiftScaleByInterval(shiftedDegrees, degree);
            DegreeFunction[] shiftedFunctions = calculatePureFunctions(shiftedDegrees);
            DegreeFunction reversedFunction;
            for (DegreeFunction shiftedFunction : shiftedFunctions) {
                if (shiftedFunction.getDegree() == 12 - degree) {
                    reversedFunction = shiftedFunction;
                    reversedFunction.reverseFunctions();
                    for (DegreeFunction function : functions) {
                        if (function.getDegree() == degree) {
                            function.addOtherFunction(reversedFunction, reversedFunctionMultiplier);
                        }
                    }
                }
            }
        }
        return functions;
    }

    private EnharmonicFunction[] calculateMediantFunctions(DegreeFunction[] pureFunctions) {
        Map<Integer, Map<Integer, DegreeFunction>> mediantFunctionsBelow = calculateSideMediantFunctions(pureFunctions,
                mediantsBelow, false);
        Map<Integer, Map<Integer, DegreeFunction>> mediantFunctionsAbove = calculateSideMediantFunctions(pureFunctions,
                mediantsAbove, true);

        EnharmonicFunction[] mediantFunctions = new EnharmonicFunction[pureFunctions.length];
        for (int i = 0; i < pureFunctions.length; i++) {
            int degree = pureFunctions[i].getDegree();
            Map<Integer, DegreeFunction> degreeBelowFunctions = mediantFunctionsBelow.get(degree);
            Map<Integer, DegreeFunction> degreeAboveFunctions = mediantFunctionsAbove.get(degree);
            mediantFunctions[i] = new EnharmonicFunction(degree);
            for (int downInterval : degreeBelowFunctions.keySet()) {
                for (int upInterval : degreeAboveFunctions.keySet()) {
                    mediantFunctions[i].addFunction(new int[]{downInterval, upInterval}, DegreeFunction
                            .sumOfFunctions(degreeBelowFunctions.get(downInterval), degreeAboveFunctions.get(upInterval)));
                }
            }
        }

        for (int i = 0; i < mediantFunctions.length; i++) {
            for (DegreeFunction mediantFunction : mediantFunctions[i].getFunctions().values()) {
                mediantFunction.addOtherFunction(pureFunctions[i], 1);
            }
        }
        return mediantFunctions;
    }

    private Map<Integer, Map<Integer, DegreeFunction>> calculateSideMediantFunctions(DegreeFunction[] pureFunctions,
                                                                                            int[] intervals,
                                                                                            boolean upOrDown) {
        Map<Integer, Map<Integer, DegreeFunction>> mediantFunctions = new HashMap<>();
        for (int interval : intervals) {
            List<DegreeFunction> currentMediantFunctions = calculateIntervalMediantFunctions(pureFunctions, interval, upOrDown);
            for (DegreeFunction function : currentMediantFunctions) {
                if (!mediantFunctions.containsKey(function.getDegree())) {
                    mediantFunctions.put(function.getDegree(), new HashMap<>());
                }
                mediantFunctions.get(function.getDegree()).put(interval, function);
            }
        }
        return mediantFunctions;
    }

    private List<DegreeFunction> calculateIntervalMediantFunctions(DegreeFunction[] pureFunctions,
                                                                          int interval,
                                                                          boolean upOrDown) {
        List<DegreeFunction> mediantFunctions = new ArrayList<>();
        DegreeFunction[] functions = new DegreeFunction[pureFunctions.length];
        for (int i = 0; i < pureFunctions.length; i++) {
            functions[i] = new DegreeFunction(pureFunctions[i].getDegree());
        }

        for (DegreeFunction pureFunction : pureFunctions) {
            for (DegreeFunction function : functions) {
                int intervalDegree = upOrDown
                        ? (pureFunction.getDegree() - interval + 12) % 12
                        : (pureFunction.getDegree() + interval) % 12;
                if (intervalDegree == function.getDegree()) {
                    function.addOtherFunction(pureFunction, mediantFunctionMultiplier);
                    mediantFunctions.add(function);
                }
            }
        }
        return mediantFunctions;
    }
}
