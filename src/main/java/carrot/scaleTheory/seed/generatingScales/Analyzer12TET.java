package carrot.scaleTheory.seed.generatingScales;

import carrot.scaleTheory.generalUtil.GeneralUtil12TET;
import carrot.scaleTheory.model.*;

import java.util.Arrays;
import java.util.Comparator;

public class Analyzer12TET {

    public static void analyzeData(ScalePhylum scalePhylum) {
        for (ScaleClass scaleClass : scalePhylum.getClasses()) {
            for (ScaleOrder scaleOrder : scaleClass.getOrders()) {
                for (ScaleFamily scaleFamily : scaleOrder.getFamilies()) {
                    for (Scale scale : scaleFamily.getScales()) {
                        scale.setEnharmonicLvl(scaleEnharmonicLvl(scale));
                        scale.setBrightnessLvl(scaleBrightnessLvl(scale));
                        scale.setBrightnessVal(scaleBrightnessVal(scale));
                    }
                    calculateCirclePositions(scaleFamily);
                    scaleFamily.getScales().sort(Comparator
                            .comparingInt(Scale::getTonicCirclePos));
                    scaleFamily.setMinEnharmonicLvl(scaleFamily.getScales()
                            .stream().map(Scale::getEnharmonicLvl)
                            .min(Integer::compareTo).orElse(scaleFamily.getOctaveSize()));
                    scaleFamily.setMaxEnharmonicLvl(scaleFamily.getScales()
                            .stream().map(Scale::getEnharmonicLvl)
                            .max(Integer::compareTo).orElse(scaleFamily.getOctaveSize()));
                }
                scaleOrder.setHomogeneityLvl(scaleOrderHomogeneityLvl(scaleOrder));
                scaleOrder.getFamilies().sort(Comparator
                        .comparingInt(ScaleFamily::getMinEnharmonicLvl)
                        .thenComparing(ScaleFamily::getMaxEnharmonicLvl));
                scaleOrder.setMinEnharmonicLvl(scaleOrder.getFamilies()
                        .stream().map(ScaleFamily::getMinEnharmonicLvl)
                        .min(Integer::compareTo).orElse(scaleOrder.getOctaveSize()));
                scaleOrder.setMaxEnharmonicLvl(scaleOrder.getFamilies()
                        .stream().map(ScaleFamily::getMaxEnharmonicLvl)
                        .max(Integer::compareTo).orElse(scaleOrder.getOctaveSize()));
            }
            scaleClass.getOrders().sort(Comparator
                    .comparingInt(ScaleOrder::getHomogeneityLvl).reversed()
                    .thenComparing(ScaleOrder::getMinEnharmonicLvl)
                    .thenComparing(ScaleOrder::getMaxEnharmonicLvl));
        }
        scalePhylum.getClasses().sort(Comparator
                .comparingInt(ScaleClass::getTonalSize));
    }

    private static void calculateCirclePositions(ScaleFamily scaleFamily) {
        Scale centerScale = scaleFamily.getScales().stream().min(Comparator
                .comparingInt((Scale scale) -> Math.abs(scale.getBrightnessLvl()))
                .thenComparing((Scale scale) -> Math.abs(scale.getBrightnessVal())))
                .orElse(scaleFamily.getScales().getFirst());
        centerScale.setTonicCirclePos(0);
        int[] currentScale = centerScale.getDegrees().clone();
        for (int i = 1; i != 0; i++) {
            GeneralUtil12TET.shiftScaleByInterval(currentScale, 7);
            for (Scale scale : scaleFamily.getScales()) {
                if (Arrays.equals(scale.getDegrees(), currentScale)) {
                    scale.setTonicCirclePos(i);
                }
            }
            if (i == 6) {
                i = -6;
            }
        }
    }

    private static int scaleEnharmonicLvl(Scale scale) {
        int[][][] limits = LimitsConfig12TET.levelLimits[scale.getTonalSize() - 1];
        for (int i = 0; i < limits.length; i++) {
            if (scaleIsInLimit(scale, limits[i])) {
                return i + 1;
            }
        }
        return limits.length + 1;
    }

    private static boolean scaleIsInLimit(Scale scale, int[][] limit) {
        for (int i = 0; i < scale.getTonalSize(); i++) {
            boolean degreeAltered = true;
            for (int j = 0; j < limit[i].length; j++) {
                if (scale.getDegree(i) == limit[i][j]) {
                    degreeAltered = false;
                }
            }
            if (degreeAltered) {
                return false;
            }
        }
        return true;
    }

    private static int scaleBrightnessLvl(Scale scale) {
        int brightnessLvl = 0;
        for (int degree : scale.getDegrees()) {
            brightnessLvl += GeneralUtil12TET.degreeBrightnessLvl(degree);
        }
        return brightnessLvl;
    }

    private static int scaleBrightnessVal(Scale scale) {
        int brightnessVal = 0;
        for (int degree : scale.getDegrees()) {
            brightnessVal += GeneralUtil12TET.degreeBrightnessVal(degree);
        }
        return brightnessVal;
    }

    private static int scaleOrderHomogeneityLvl(ScaleOrder scaleOrder) {
        double deviationSum = 0;
        double optimalInterval = (double) scaleOrder.getOctaveSize()
                / scaleOrder.getTonalSize();
        for (int i = 0; i < scaleOrder.getIntervals().length; i++) {
            deviationSum += Math.abs(1 - (i + 1) / optimalInterval)
                    * scaleOrder.getIntervals()[i];
        }
        double homogeneityLvl = 1 - deviationSum / scaleOrder.getTonalSize();
        return (int) (homogeneityLvl * 100);
    }
}
