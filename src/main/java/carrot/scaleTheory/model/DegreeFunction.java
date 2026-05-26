package carrot.scaleTheory.model;

public class DegreeFunction {

    private final int degree;

    private double tonicFunction = 0;
    private double dominantFunction = 0;
    private double subdominantFunction = 0;

    public DegreeFunction(int degree) {
        this.degree = degree;
    }

    public int getDegree() {
        return degree;
    }

    public double getTonicFunction() {
        return tonicFunction;
    }

    public double getDominantFunction() {
        return dominantFunction;
    }

    public double getSubdominantFunction() {
        return subdominantFunction;
    }

    public void setTonicFunction(double tonicFunction) {
        this.tonicFunction = tonicFunction;
    }

    public void setDominantFunction(double dominantFunction) {
        this.dominantFunction = dominantFunction;
    }

    public void setSubdominantFunction(double subdominantFunction) {
        this.subdominantFunction = subdominantFunction;
    }

    public void addOtherFunction(DegreeFunction other, double multiplier) {
        this.tonicFunction += other.tonicFunction * multiplier;
        this.dominantFunction += other.dominantFunction * multiplier;
        this.subdominantFunction += other.subdominantFunction * multiplier;
    }

    public void reverseFunctions() {
        double temp = this.dominantFunction;
        this.dominantFunction = this.subdominantFunction;
        this.subdominantFunction = temp;
    }

    public boolean isEmpty() {
        return this.tonicFunction == 0
                && this.dominantFunction == 0
                && this.subdominantFunction == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DegreeFunction function = (DegreeFunction) o;
        return degree == function.degree
                && Double.compare(tonicFunction, function.tonicFunction) == 0
                && Double.compare(dominantFunction, function.dominantFunction) == 0
                && Double.compare(subdominantFunction, function.subdominantFunction) == 0;
    }

    public static DegreeFunction sumOfFunctions(DegreeFunction a, DegreeFunction b) {
        DegreeFunction sum = new DegreeFunction(a.getDegree());
        sum.addOtherFunction(a, 1);
        sum.addOtherFunction(b, 1);
        return sum;
    }
}
