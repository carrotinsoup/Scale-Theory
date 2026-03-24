package carrot.scaleTheory.seed.generatingScales;

public class Util {

    public static boolean patternIsCanonical(int[] pattern) {
        int[] shifted = pattern.clone();
        for (int i = 0; i < pattern.length - 1; i++) {
            shifted = shiftArray(shifted);
            if (!patternIsBigger(pattern, shifted)) {
                return false;
            }
        }
        return true;
    }

    public static boolean patternIsBigger(int[] pattern, int[] other) {
        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i] > other[i]) {
                return true;
            }
            if (pattern[i] < other[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] shiftArray(int[] array) {
        int[] shifted = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            shifted[i] = array[(i + 1) % array.length];
        }
        return shifted;
    }
}
