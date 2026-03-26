package allnew.okk.product.command;

import allnew.okk.product.model.BaseProduct;

// week 5, pattern command 4 Jakub Marciniuk
public class UpdateProductCommand implements ProductCommand{
    private final BaseProduct product;
    private final double newPrice;
    private double oldPrice;

    public UpdateProductCommand(BaseProduct product, double newPrice) {
        this.product = product;
        this.newPrice = newPrice;
    }

    @Override
    public void execute() {
        oldPrice = product.getPrice();
        product.setPrice(newPrice);
    }

    @Override
    public void undo() {
        product.setPrice(oldPrice);
    }
}
// end of week 5, pattern command 4
