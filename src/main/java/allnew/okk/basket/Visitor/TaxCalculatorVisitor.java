package allnew.okk.basket.Visitor;

import allnew.okk.product.model.CompanyProduct;
import allnew.okk.product.model.PrivateProduct;

public class TaxCalculatorVisitor implements  BasketVisitor{
    private static final double COMPANY_TAX_RATE = 0.23;
    private static final double PRIVATE_TAX_RATE = 0.10;

    private double totalTax = 0;

    @Override
    public void visit(CompanyProduct product) {
        totalTax += product.getPrice() * COMPANY_TAX_RATE;
    }

    @Override
    public void visit(PrivateProduct product) {
        totalTax += product.getPrice() * PRIVATE_TAX_RATE;
    }

    public double getTotalTax() { return totalTax; }
}