package allnew.okk.product.visitor;

import allnew.okk.product.model.CompanyProduct;
import allnew.okk.product.model.PrivateProduct;

// Week 6, pattern visitor Marciniuk
public class StockValueVisitor implements ProductVisitor {
    private static final double COMPANY_COEFFICIENT = 1.15;
    private static final double PRIVATE_COEFFICIENT = 1.0;

    private double totalStockValue = 0;

    @Override
    public void visit(PrivateProduct product) {
        double value = product.getPrice() * PRIVATE_COEFFICIENT;
        System.out.println("StockValue [private]: " + product.getName() + " = " + value + " zł");
        totalStockValue += value;
    }

    @Override
    public void visit(CompanyProduct product) {
        double value = product.getPrice() * COMPANY_COEFFICIENT;
        System.out.println("StockValue [company]: " + product.getName() + " = " + value + " zł");
        totalStockValue += value;
    }

    public double getTotalStockValue() { return totalStockValue; }
}
// End Week 6, pattern visitor Marciniuk