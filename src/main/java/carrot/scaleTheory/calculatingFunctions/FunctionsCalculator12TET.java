package carrot.scaleTheory.calculatingFunctions;

import carrot.scaleTheory.generalUtil.GeneralUtil12TET;
import carrot.scaleTheory.model.*;

import java.util.*;

public class FunctionsCalculator12TET {

    private static final int tonicFunctionValue = 4;
    private static final int tritoneFunctionValue = 4;

    private static final double equalTritonesMultiplier = 0.5;

    private static final double mediantFunctionMultiplier = 0.5;
    private static final double adjacentFunctionMultiplier = 0.25;
    private static final double reversedFunctionMultiplier = 0.25;

    public static void calculateFunctions(Scale scale) {
        DegreeFunction[] pureFunctions = calculatePureFunctions(scale.getDegrees());
        DegreeFunction[] adjacentFunctions = calculateAdjacentFunctions(scale.getDegrees());
        DegreeFunction[] reversedFunctions = calculateReversedFunctions(scale.getDegrees());
        Map<Integer, Map<int[], DegreeFunction>> mediantFunctions = calculateMediantFunctions(
                pureFunctions, new int[] {3, 4}, new int[] {3, 4});
        //System.out.println();
    }

    private static DegreeFunction[] calculatePureFunctions(int[] degrees) {
        DegreeFunction[] functions = new DegreeFunction[degrees.length];
        for (int i = 0; i < degrees.length; i++) {
            functions[i] = new DegreeFunction(degrees[i]);
        }

        functions[0].setTonicFunction(tonicFunctionValue);

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
                        function.setDominantFunction(tritoneFunctionValue * equalTritonesMultiplier);
                        function.setSubdominantFunction(tritoneFunctionValue * equalTritonesMultiplier);
                    }
                }
            }
            else {
                for (DegreeFunction function : functions) {
                    if (function.getDegree() == tritone[0]) {
                        function.setSubdominantFunction(tritoneFunctionValue);
                    }
                    else if (function.getDegree() == tritone[1]) {
                        function.setDominantFunction(tritoneFunctionValue);
                    }
                }
            }
        }
        return functions;
    }

    private static DegreeFunction[] calculateAdjacentFunctions(int[] degrees) {
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

    private static DegreeFunction[] calculateReversedFunctions(int[] degrees) {
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
                if (shiftedFunction.getDegree() == GeneralUtil12TET.octaveSize - degree) {
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

    private static Map<Integer, Map<int[], DegreeFunction>> calculateMediantFunctions(DegreeFunction[] pureFunctions,
                                                                                      int[] intervalsDown,
                                                                                      int[] intervalsUp) {
        Map<Integer, Map<Integer, DegreeFunction>> downMediantFunctions = calculateSideMediantFunctions(pureFunctions,
                intervalsDown, false);
        Map<Integer, Map<Integer, DegreeFunction>> upMediantFunctions = calculateSideMediantFunctions(pureFunctions,
                intervalsUp, true);

        Map<Integer, Map<int[], DegreeFunction>> mediantFunctions = new HashMap<>();
        for (DegreeFunction pureFunction : pureFunctions) {
            int degree = pureFunction.getDegree();
            Map<Integer, DegreeFunction> degreeDownFunctions = downMediantFunctions.get(degree);
            Map<Integer, DegreeFunction> degreeUpFunctions = upMediantFunctions.get(degree);
            mediantFunctions.put(degree, new HashMap<>());
            for (int downInterval : degreeDownFunctions.keySet()) {
                for (int upInterval : degreeUpFunctions.keySet()) {
                    mediantFunctions.get(degree).put(new int[]{downInterval, upInterval}, DegreeFunction
                            .sumOfFunctions(degreeDownFunctions.get(downInterval), degreeUpFunctions.get(upInterval)));
                }
            }
        }
        return mediantFunctions;
    }

    private static Map<Integer, Map<Integer, DegreeFunction>> calculateSideMediantFunctions(DegreeFunction[] pureFunctions,
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

    private static List<DegreeFunction> calculateIntervalMediantFunctions(DegreeFunction[] pureFunctions,
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
                        ? (pureFunction.getDegree() - interval + GeneralUtil12TET.octaveSize) % GeneralUtil12TET.octaveSize
                        : (pureFunction.getDegree() + interval)% GeneralUtil12TET.octaveSize;
                if (intervalDegree == function.getDegree()) {
                    function.addOtherFunction(pureFunction, mediantFunctionMultiplier);
                    mediantFunctions.add(function);
                }
            }
        }
        return mediantFunctions;
    }
}
