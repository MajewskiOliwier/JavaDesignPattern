package allnew.okk.product.calculator;

// Week 8, Dependency Inversion Principle Marciniuk
public abstract class AbstractPriceCalculator implements IPriceCalculator {
    protected final double taxRate;
    protected AbstractPriceCalculator(double taxRate) {
        this.taxRate = taxRate;
    }
}
// End Week 8, Dependency Inversion Principle Marciniuk