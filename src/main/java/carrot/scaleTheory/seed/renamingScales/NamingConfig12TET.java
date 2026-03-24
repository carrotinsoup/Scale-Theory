package carrot.scaleTheory.seed.renamingScales;

import java.util.Arrays;

public class NamingConfig12TET {

    public static final boolean familyDiatonicNaming = true;
    public static final boolean scaleAlterationsFromNatural = false;

    public static final int scaleByPresentNamingMax = 4;
    public static final int scaleByAbsentNamingMin = 9;

    public static final String phylumPostfix = "TET";
    public static final String classPostfix = "Тоновий";
    public static final String scaleByPresentPrefix = "";
    public static final String scaleByAbsentPrefix = "Без";

    public static final char flatChar = '♭';
    public static final char sharpChar = '#';
    public static final char naturalChar = '♮';

    public static final String[] intervalNames = new String[] {
            "П",
            "Т",
            "М3",
            "В3",
            "Ч4",
            "3Т",
            "Ч5",
            "М6",
            "В6",
            "М7",
            "В7",
            "Октава"
    };

    public static final String familyDiatonicName = "Діатонічна";

    public static final int[][] familyDiatonicPatterns = {
            new int[] {12},
            new int[] {7, 5},
            new int[] {5, 5, 2},
            new int[] {5, 2, 3, 2},
            new int[] {3, 2, 3, 2, 2},
            new int[] {3, 2, 2, 1, 2, 2},
            new int[] {2, 2, 2, 1, 2, 2, 1},
            new int[] {2, 2, 1, 2, 2, 1, 1, 1},
            new int[] {2, 2, 1, 1, 1, 2, 1, 1, 1},
            new int[] {2, 1, 1, 1, 2, 1, 1, 1, 1, 1},
            new int[] {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public record ScaleName(int[] degrees, String name) {
        public boolean matches(int[] otherDegrees) {
            return Arrays.equals(this.degrees, otherDegrees);
        }
    }

    private static final ScaleName[] diatonic_1 = {
            new ScaleName(new int[] {0}, "Ізотонічний")
    };

    private static final ScaleName[] diatonic_2 = {
            new ScaleName(new int[] {0, 7}, "Квінтовий"),
            new ScaleName(new int[] {0, 5}, "Квартовий")
    };

    private static final ScaleName[] diatonic_3 = {
            new ScaleName(new int[] {0, 2, 7}, "Двічі-Квінтовий"),
            new ScaleName(new int[] {0, 5, 7}, "Кварто-Квінтовий"),
            new ScaleName(new int[] {0, 5, 10}, "Двічі-Квартовий")
    };

    private static final ScaleName[] diatonic_4 = {
            new ScaleName(new int[] {0, 2, 7, 9}, "<->>Іонійський"),
            new ScaleName(new int[] {0, 2, 5, 7}, "<->>Міксолідійський"),
            new ScaleName(new int[] {0, 5, 7, 10}, "<<->Еолійський"),
            new ScaleName(new int[] {0, 3, 5, 10}, "<<->Фрігійський")
    };

    private static final ScaleName[] diatonic_5 = {
            new ScaleName(new int[] {0, 2, 4, 7, 9}, "<->Іонійський"),
            new ScaleName(new int[] {0, 2, 5, 7, 9}, "<->Міксолідійський"),
            new ScaleName(new int[] {0, 2, 5, 7, 10}, "<->Дорійський"),
            new ScaleName(new int[] {0, 3, 5, 7, 10}, "<->Еолійський"),
            new ScaleName(new int[] {0, 3, 5, 8, 10}, "<->Фрігійський")
    };

    private static final ScaleName[] diatonic_6 = {
            new ScaleName(new int[] {0, 2, 4, 7, 9, 11}, "<-Іонійський"),
            new ScaleName(new int[] {0, 2, 4, 5, 7, 9}, "<-Міксолідійський"),
            new ScaleName(new int[] {0, 2, 5, 7, 9, 10}, "<-Дорійський"),
            new ScaleName(new int[] {0, 2, 3, 5, 7, 10}, "->Дорійський"),
            new ScaleName(new int[] {0, 3, 5, 7, 8, 10}, "->Еолійський"),
            new ScaleName(new int[] {0, 1, 3, 5, 8, 10}, "->Фрігійський")
    };

    private static final ScaleName[] diatonic_7 = {
            new ScaleName(new int[] {0, 2, 4, 6, 7, 9, 11}, "Лідійський"),
            new ScaleName(new int[] {0, 2, 4, 5, 7, 9, 11}, "Іонійський"),
            new ScaleName(new int[] {0, 2, 4, 5, 7, 9, 10}, "Міксолідійський"),
            new ScaleName(new int[] {0, 2, 3, 5, 7, 9, 10}, "Дорійський"),
            new ScaleName(new int[] {0, 2, 3, 5, 7, 8, 10}, "Еолійський"),
            new ScaleName(new int[] {0, 1, 3, 5, 7, 8, 10}, "Фрігійський"),
            new ScaleName(new int[] {0, 1, 3, 5, 6, 8, 10}, "Локрійський")
    };

    private static final ScaleName[] diatonic_8 = {
            new ScaleName(new int[] {0, 1, 2, 4, 6, 7, 9, 11}, "<--Лідійський"),
            new ScaleName(new int[] {0, 2, 4, 5, 6, 7, 9, 11}, "<--Іонійський"),
            new ScaleName(new int[] {0, 2, 4, 5, 7, 9, 10, 11}, "<--Міксолідійський"),
            new ScaleName(new int[] {0, 2, 3, 4, 5, 7, 9, 10}, "<--Дорійський"),
            new ScaleName(new int[] {0, 2, 3, 5, 7, 8, 9, 10}, "-->Дорійський"),
            new ScaleName(new int[] {0, 1, 2, 3, 5, 7, 8, 10}, "-->Еолійський"),
            new ScaleName(new int[] {0, 1, 3, 5, 6, 7, 8, 10}, "-->Фрігійський"),
            new ScaleName(new int[] {0, 1, 3, 5, 6, 8, 10, 11}, "-->Локрійський")
    };

    private static final ScaleName[] diatonic_9 = {
            new ScaleName(new int[] {0, 1, 2, 4, 6, 7, 8, 9, 11}, "Без-05-10-03"),
            new ScaleName(new int[] {0, 1, 2, 4, 5, 6, 7, 9, 11}, "Без-10-03-08"),
            new ScaleName(new int[] {0, 2, 4, 5, 6, 7, 9, 10, 11}, "Без-03-08-01"),
            new ScaleName(new int[] {0, 2, 3, 4, 5, 7, 9, 10, 11}, "Без-08-01-06"),
            new ScaleName(new int[] {0, 2, 3, 4, 5, 7, 8, 9, 10}, "Без-01-06-11"),
            new ScaleName(new int[] {0, 1, 2, 3, 5, 7, 8, 9, 10}, "Без-06-11-04"),
            new ScaleName(new int[] {0, 1, 2, 3, 5, 6, 7, 8, 10}, "Без-11-04-09"),
            new ScaleName(new int[] {0, 1, 3, 5, 6, 7, 8, 10, 11}, "Без-04-09-02"),
            new ScaleName(new int[] {0, 1, 3, 4, 5, 6, 8, 10, 11}, "Без-09-02-07")
    };

    private static final ScaleName[] diatonic_10 = {
            new ScaleName(new int[] {0, 1, 2, 3, 4, 6, 7, 8, 9, 11}, "Без-05-10"),
            new ScaleName(new int[] {0, 1, 2, 4, 5, 6, 7, 8, 9, 11}, "Без-10-03"),
            new ScaleName(new int[] {0, 1, 2, 4, 5, 6, 7, 9, 10, 11}, "Без-03-08"),
            new ScaleName(new int[] {0, 2, 3, 4, 5, 6, 7, 9, 10, 11}, "Без-08-01"),
            new ScaleName(new int[] {0, 2, 3, 4, 5, 7, 8, 9, 10, 11}, "Без-01-06"),
            new ScaleName(new int[] {0, 1, 2, 3, 4, 5, 7, 8, 9, 10}, "Без-06-11"),
            new ScaleName(new int[] {0, 1, 2, 3, 5, 6, 7, 8, 9, 10}, "Без-11-04"),
            new ScaleName(new int[] {0, 1, 2, 3, 5, 6, 7, 8, 10, 11}, "Без-04-09"),
            new ScaleName(new int[] {0, 1, 3, 4, 5, 6, 7, 8, 10, 11}, "Без-09-02"),
            new ScaleName(new int[] {0, 1, 3, 4, 5, 6, 8, 9, 10, 11}, "Без-02-07")
    };

    private static final ScaleName[] diatonic_11 = {
            new ScaleName(new int[] {0, 1, 2, 3, 4, 6, 7, 8, 9, 10, 11}, "Без-05"),
            new ScaleName(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11}, "Без-10"),
            new ScaleName(new int[] {0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 11}, "Без-03"),
            new ScaleName(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11}, "Без-08"),
            new ScaleName(new int[] {0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, "Без-01"),
            new ScaleName(new int[] {0, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11}, "Без-06"),
            new ScaleName(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, "Без-11"),
            new ScaleName(new int[] {0, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11}, "Без-04"),
            new ScaleName(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11}, "Без-09"),
            new ScaleName(new int[] {0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11}, "Без-02"),
            new ScaleName(new int[] {0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11}, "Без-07")
    };

    private static final ScaleName[] diatonic_12 = {
            new ScaleName(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, "Хроматичний")
    };

    public static final ScaleName[][] diatonicScales = {
            diatonic_1,
            diatonic_2,
            diatonic_3,
            diatonic_4,
            diatonic_5,
            diatonic_6,
            diatonic_7,
            diatonic_8,
            diatonic_9,
            diatonic_10,
            diatonic_11,
            diatonic_12
    };
    public static final ScaleName[] naturalScales = {
            diatonic_1[0],
            diatonic_2[0],
            diatonic_3[0],
            diatonic_4[0],
            diatonic_5[1],
            diatonic_6[1],
            diatonic_7[1],
            diatonic_8[1],
            diatonic_9[2],
            diatonic_10[2],
            diatonic_11[2],
            diatonic_12[0]
    };
}
