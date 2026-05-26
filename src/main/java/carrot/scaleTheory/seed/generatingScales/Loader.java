package carrot.scaleTheory.seed.generatingScales;

import carrot.scaleTheory.calculatingFunctions.FunctionsCalculator12TET;
import carrot.scaleTheory.model.ScalePhylum;
import carrot.scaleTheory.seed.renamingScales.NameGenerator;

public class Loader {

    public static void main(String[] args) {
        ScalePhylum scalePhylum = Generator.generateData(12);
        Analyzer12TET.analyzeData(scalePhylum);
        NameGenerator.renameData(scalePhylum);
        //System.out.println("Ready!");

        FunctionsCalculator12TET.calculateFunctions(scalePhylum.getClasses().get(6).getOrders().get(1)
                .getFamilies().get(1).getScales().get(1));
    }
}
