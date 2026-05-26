package carrot.scaleTheory.seed.generatingScales;

import carrot.scaleTheory.model.*;

import java.util.ArrayList;
import java.util.Arrays;


public class Generator {

    public static ScalePhylum generateData(int octaveSize) {
        ScalePhylum scalePhylum = new ScalePhylum(octaveSize);
        generateClasses(scalePhylum);
        for (ScaleClass scaleClass : scalePhylum.getClasses()) {
            generateOrders(scaleClass);
            for (ScaleOrder scaleOrder : scaleClass.getOrders()) {
                generateFamilies(scaleOrder);
                for (ScaleFamily scaleFamily : scaleOrder.getFamilies()) {
                    generateScales(scaleFamily);
                }
            }
        }
        return scalePhylum;
    }

    private static void generateClasses(ScalePhylum scalePhylum) {
        for (int i = 1; i <= scalePhylum.getOctaveSize(); i++) {
            scalePhylum.getClasses().add(new ScaleClass(scalePhylum, i));
        }
    }

    private static void generateOrders(ScaleClass scaleClass) {
        int maxInterval = scaleClass.getOctaveSize() + 1 - scaleClass.getTonalSize();
        generateOrdersRecursive(scaleClass,
                new int[maxInterval],
                maxInterval - 1,
                0,
                0);
    }

    private static void generateOrdersRecursive(ScaleClass scaleClass,
                                                int[] currentIntervals,
                                                int currentPointer,
                                                int currentCount,
                                                int currentSum) {
        if (currentPointer < 0) {
            if (currentCount == scaleClass.getTonalSize() &&
                    currentSum == scaleClass.getOctaveSize()) {
                scaleClass.getOrders().add(new ScaleOrder(scaleClass, currentIntervals));
            }
            return;
        }
        for (int i = 0; i <= Math.min(scaleClass.getTonalSize(),
                scaleClass.getOctaveSize() / (currentPointer + 1)); i++) {
            int[] intervals = currentIntervals.clone();
            int pointer = currentPointer;
            int count = currentCount;
            int sum = currentSum;
            intervals[pointer] = i;
            count += i;
            sum += (pointer + 1) * i;
            pointer--;
            generateOrdersRecursive(scaleClass, intervals, pointer, count, sum);
        }
    }

    private static void generateFamilies(ScaleOrder scaleOrder) {
        generateFamiliesRecursive(scaleOrder,
                new int[scaleOrder.getTonalSize()],
                0,
                scaleOrder.getIntervals());
    }

    private static void generateFamiliesRecursive(ScaleOrder scaleOrder,
                                           int[] currentPattern,
                                           int currentPointer,
                                           int[] currentIntervals) {
        if (currentPointer == scaleOrder.getTonalSize()) {
            if (Util.patternIsCanonical(currentPattern)) {
                scaleOrder.getFamilies().add(new ScaleFamily(scaleOrder, currentPattern));
            }
            return;
        }
        for (int i = 0; i < currentIntervals.length; i++) {
            if (currentIntervals[i] == 0) {
                continue;
            }
            int[] pattern = currentPattern.clone();
            int[] intervals = currentIntervals.clone();
            int pointer = currentPointer;
            pattern[pointer] = i + 1;
            intervals[i]--;
            pointer++;
            generateFamiliesRecursive(scaleOrder, pattern, pointer, intervals);
        }
    }

    private static void generateScales(ScaleFamily scaleFamily) {
        int[] pattern = scaleFamily.getPattern();
        for (int i = 0; i < scaleFamily.getTonalSize(); i++) {
            int[] degrees = new int[pattern.length];
            int position = 0;
            for (int j = 0; j < pattern.length; j++) {
                degrees[j] = position;
                position += pattern[j];
            }
            Scale newScale = new Scale(scaleFamily, degrees);
            boolean scaleIsUnique = true;
            for (Scale scale : scaleFamily.getScales()) {
                if (Arrays.equals(scale.getDegrees(), newScale.getDegrees())) {
                    scaleIsUnique = false;
                }
            }
            if (scaleIsUnique) {
                scaleFamily.getScales().add(newScale);
            }
            pattern = Util.shiftArray(pattern);
        }
    }
}
