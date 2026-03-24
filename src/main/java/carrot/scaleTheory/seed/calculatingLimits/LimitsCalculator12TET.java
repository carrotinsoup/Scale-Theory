package carrot.scaleTheory.seed.calculatingLimits;

import carrot.scaleTheory.generalUtil.GeneralUtil12TET;

import java.util.*;

public class LimitsCalculator12TET {

    public static void main(String[] args) {
        viewLimits(7);
    }

    public static void viewLimits(int tonalSize) {
        int minLimit = tonalSize / 2;
        int[][][] limits = new int[GeneralUtil12TET.octaveSize][][];
        for (int i = tonalSize - 1; i < GeneralUtil12TET.octaveSize; i++) {
            limits[i] = getLimitsInFifthRange(tonalSize, i);
        }
        for (int i = minLimit; i < tonalSize - 1; i++) {
            limits[i] = getLimitsInReducedRange(limits[tonalSize - 1], i);
        }
        for (int i = 0; i < minLimit; i++) {
            limits[i] = new int[tonalSize][0];
        }
        for (int i = 0; i < limits.length; i++) {
            System.out.print(String.format("%02d", i + 1) + ": ");
            for (int j = 0; j < limits[i].length; j++) {
                System.out.print(Arrays.toString(limits[i][j]).replace('[', '{').replace(']', '}'));
                if (j != limits[i].length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    private static int[][] getLimitsInReducedRange(int[][] originalLimits, int fifthRange) {
        int[][] limits = new int[originalLimits.length][];
        for (int i = 0; i < originalLimits.length; i++) {
            int[] degreeLimits = new int[0];
            for (int j = 0; j < originalLimits[i].length; j++) {
                if (GeneralUtil12TET.fourthFifthAbsIndex(originalLimits[i][j]) <= fifthRange) {
                    int[] newLimits = new int[degreeLimits.length + 1];
                    int k;
                    for (k = 0; k < degreeLimits.length; k++) {
                        newLimits[k] = degreeLimits[k];
                    }
                    newLimits[k] = originalLimits[i][j];
                    degreeLimits = newLimits;
                }
            }
            limits[i] = degreeLimits;
        }
        return limits;
    }

    private static int[][] getLimitsInFifthRange(int tonalSize, int fifthRange) {
        List<int[]> scales = getScalesInRange(tonalSize, fifthRange);
        List<Integer>[] limitsList = new List[tonalSize];
        for (int i = 0; i < tonalSize; i++) {
            limitsList[i] = new ArrayList<>();
        }
        for (int i = 0; i < scales.size(); i++) {
            for (int j = 0; j < tonalSize; j++) {
                int[] mode = getMode(scales.get(i), j);
                for (int k = 0; k < tonalSize; k++) {
                    if (!limitsList[k].contains(mode[k])) {
                        limitsList[k].add(mode[k]);
                    }
                }
            }
        }
        for (int i = 0; i < limitsList.length; i++) {
            limitsList[i].sort(Integer::compareTo);
        }
        int[][] limits = new int[tonalSize][];
        for (int i = 0; i < tonalSize; i++) {
            limits[i] = new int[limitsList[i].size()];
            for (int j = 0; j < limitsList[i].size(); j++) {
                limits[i][j] = limitsList[i].get(j);
            }
        }
        return limits;
    }

    private static List<int[]> getScalesInRange(int tonalSize, int fifthRange) {
        List<int[]> scales = new ArrayList<>();
        if (tonalSize == 1) {
            scales.add(new int[]{0});
            return scales;
        }
        int[] startingScale = new int[tonalSize];
        startingScale[0] = 0;
        List<Integer> range = new ArrayList<>();
        for (int i = 1; i <= fifthRange; i++) {
            range.add(GeneralUtil12TET.fourthFifthDegree(i));
        }
        getScalesInRangeRecursive(scales, range, startingScale, 1);
        return scales;
    }

    private static void getScalesInRangeRecursive(List<int[]> scales, List<Integer> currentRange, int[] currentScale, int currentPointer) {
        if (currentPointer == currentScale.length) {
            Arrays.sort(currentScale);
            if (scales.stream().noneMatch(a -> Arrays.equals(a, currentScale))) {
                scales.add(currentScale);
            }
            return;
        }
        for (int i = 0; i < currentRange.size(); i++) {
            List<Integer> range = new ArrayList<>(currentRange);
            int[] scale = currentScale.clone();
            int pointer = currentPointer;
            scale[pointer] = range.get(i);
            range.remove(i);
            pointer++;
            getScalesInRangeRecursive(scales, range, scale, pointer);
        }
    }

    private static int[] getMode(int[] parentScale, int modeIndex) {
        int[] modeScale = parentScale.clone();
        int scaleShift = modeScale[modeIndex];
        for (int i = 0; i < modeScale.length; i++) {
            modeScale[i] = (modeScale[i] - scaleShift +
                    GeneralUtil12TET.octaveSize) % GeneralUtil12TET.octaveSize;
        }
        Arrays.sort(modeScale);
        return modeScale;
    }
}
