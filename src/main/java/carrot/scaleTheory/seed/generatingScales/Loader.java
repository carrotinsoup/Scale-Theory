package carrot.scaleTheory.seed.generatingScales;

import carrot.scaleTheory.model.ScalePhylum;
import carrot.scaleTheory.seed.renamingScales.NameGenerator;

public class Loader {

    public static void main(String[] args) {
        ScalePhylum scalePhylum = Generator.generateData(12);
        Analyzer12TET.analyzeData(scalePhylum);
        NameGenerator.renameData(scalePhylum);
        System.out.println("Ready!");
    }
}
