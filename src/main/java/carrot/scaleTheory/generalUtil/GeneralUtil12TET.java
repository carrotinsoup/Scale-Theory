package carrot.scaleTheory.generalUtil;

import java.util.Arrays;

public class GeneralUtil12TET {

    public static final int octaveSize = 12;

    private static final int[] ascendingFourths = new int[] {0, 5, 10, 3, 8, 1, 6};
    private static final int[] ascendingFifths = new int[] {0, 7, 2, 9, 4, 11, 6};

    public static int fourthFifthDegree(int index) {
        return (7 * index) % octaveSize;
    }

    public static int fourthFifthAbsIndex(int degree) {
        for (int i = 0; i < 7; i++) {
            if (ascendingFourths[i] == degree || ascendingFifths[i] == degree) {
                return i;
            }
        }
        return 0;
    }

    public static int degreeBrightnessLvl(int degree) {
        if (degree == 0 || degree == 6) {
            return 0;
        }
        for (int i = 0; i < 7; i++) {
            if (ascendingFourths[i] == degree) {
                return -1;
            }
            if (ascendingFifths[i] == degree) {
                return 1;
            }
        }
        return 0;
    }

    public static int degreeBrightnessVal(int degree) {
        for (int i = 1; i < 6; i++) {
            if (ascendingFourths[i] == degree) {
                return -i;
            }
        }
        for (int i = 1; i < 6; i++) {
            if (ascendingFifths[i] == degree) {
                return i;
            }
        }
        return 0;
    }

    public static void shiftScaleByInterval(int[] scale, int interval) {
        for (int i = 0; i < scale.length; i++) {
            scale[i] = (scale[i] - interval + octaveSize) % octaveSize;
        }
        Arrays.sort(scale);
    }
}
