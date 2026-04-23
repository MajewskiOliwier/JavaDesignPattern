package allnew.okk.product.calculator;

public class StandardPriceCalculator extends AbstractPriceCalculator {
    public StandardPriceCalculator(double taxRate) {
        super(taxRate);
    }
    @Override
    public double calculate(double basePrice) {
        return basePrice + (basePrice * taxRate);
    }
}