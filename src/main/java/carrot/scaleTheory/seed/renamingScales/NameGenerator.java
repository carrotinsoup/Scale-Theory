package carrot.scaleTheory.seed.renamingScales;

import carrot.scaleTheory.model.*;

import carrot.scaleTheory.seed.renamingScales.NamingConfig12TET.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NameGenerator {

    public static void renameData(ScalePhylum scalePhylum) {
        scalePhylum.setName(phylumName(scalePhylum));
        for (ScaleClass scaleClass : scalePhylum.getClasses()) {
            scaleClass.setName(className(scaleClass));
            for (ScaleOrder scaleOrder : scaleClass.getOrders()) {
                scaleOrder.setName(orderName(scaleOrder));
                for (ScaleFamily scaleFamily : scaleOrder.getFamilies()) {
                    scaleFamily.setName(familyName(scaleFamily));
                    for (Scale scale : scaleFamily.getScales()) {
                        scale.setName(scaleName(scale));
                    }
                }
            }
        }
    }

    private static String phylumName(ScalePhylum scalePhylum) {
        return scalePhylum.getOctaveSize() + "-" + NamingConfig12TET.phylumPostfix;
    }

    private static String className(ScaleClass scaleClass) {
        return scaleClass.getTonalSize() + "-" + NamingConfig12TET.classPostfix;
    }

    private static String orderName(ScaleOrder scaleOrder) {
        StringBuilder orderName = new StringBuilder();
        for (int i = scaleOrder.getIntervals().length - 1; i >= 0; i--) {
            if (scaleOrder.getIntervals()[i] > 0) {
                orderName.append(scaleOrder.getIntervals()[i]).append("-")
                        .append(NamingConfig12TET.intervalNames[i]).append(" ");
            }
        }
        return orderName.toString();
    }

    private static String familyName(ScaleFamily scaleFamily) {
        if (NamingConfig12TET.familyDiatonicNaming) {
            if (Arrays.equals(scaleFamily.getPattern(), NamingConfig12TET
                    .familyDiatonicPatterns[scaleFamily.getTonalSize() - 1])) {
                return NamingConfig12TET.familyDiatonicName;
            }
        }
        StringBuilder familyName = new StringBuilder();
        for (int interval : scaleFamily.getPattern()) {
            familyName.append(NamingConfig12TET.intervalNames[interval - 1]);
        }
        return familyName.toString();
    }

    private static String scaleName(Scale scale) {
        if (scale.getTonalSize() <= NamingConfig12TET.scaleByPresentNamingMax) {
            return scaleNameByPresent(scale);
        }
        if (scale.getTonalSize() >= NamingConfig12TET.scaleByAbsentNamingMin) {
            return scaleNameByAbsent(scale);
        }
        int minDifference = scale.getTonalSize() * scale.getOctaveSize();
        for (ScaleName diatonic : NamingConfig12TET.diatonicScales[scale.getTonalSize() - 1]) {
            int difference = scalesDifference(scale.getDegrees(), diatonic.degrees());
            minDifference = Math.min(minDifference, difference);
        }
        List<String> names = new ArrayList<>();
        for (ScaleName diatonic : NamingConfig12TET.diatonicScales[scale.getTonalSize() - 1]) {
            if (scalesDifference(scale.getDegrees(), diatonic.degrees()) == minDifference) {
                names.add(nameAsReference(scale, diatonic));
            }
        }
        return String.join(", ", names);
    }

    private static int scalesDifference(int[] scale, int[] otherScale) {
        int difference = 0;
        for (int i = 0; i < scale.length; i++) {
            if (scale[i] != otherScale[i]) {
                difference += Math.abs(scale[i] - otherScale[i]);
            }
        }
        return difference;
    }

    private static String nameAsReference(Scale scale, ScaleName reference) {
        ScaleName naturalScale = reference;
        if (NamingConfig12TET.scaleAlterationsFromNatural) {
            naturalScale = NamingConfig12TET.naturalScales[scale.getTonalSize() - 1];
        }
        StringBuilder name = new StringBuilder(reference.name() + " ");
        for (int i = 0; i < scale.getTonalSize(); i++) {
            if (scale.getDegree(i) != reference.degrees()[i]) {
                if (scale.getDegree(i) == naturalScale.degrees()[i]) {
                    name.append(NamingConfig12TET.naturalChar).append(i + 1);
                }
                else{
                    char alterationChar = scale.getDegree(i) < naturalScale.degrees()[i] ?
                            NamingConfig12TET.flatChar : NamingConfig12TET.sharpChar;
                    name.append(String.valueOf(alterationChar)
                            .repeat(Math.max(0, Math.abs(scale.getDegree(i) -
                                    naturalScale.degrees()[i]))));
                    name.append(i + 1);
                }
            }
        }
        return name.toString();
    }

    private static String scaleNameByPresent(Scale scale) {
        return NamingConfig12TET.scaleByPresentPrefix +
                Arrays.toString(scale.getDegrees())
                .replace(", ", "-")
                .replace("[", "")
                .replace("]", "");
    }

    private static String scaleNameByAbsent(Scale scale) {
        StringBuilder scaleName = new StringBuilder();
        scaleName.append(NamingConfig12TET.scaleByAbsentPrefix).append(" ");
        for (int i = 1; i < scale.getOctaveSize(); i++) {
            boolean absent = true;
            for (int degree : scale.getDegrees()) {
                if (i == degree) {
                    absent = false;
                    break;
                }
            }
            if (absent) {
                scaleName.append(i).append("-");
            }
        }
        scaleName.deleteCharAt(scaleName.length() - 1);
        return scaleName.toString();
    }
}
