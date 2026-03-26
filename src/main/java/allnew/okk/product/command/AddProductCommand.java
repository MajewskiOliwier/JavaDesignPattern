package allnew.okk.product.command;

import allnew.okk.product.model.BaseProduct;
import allnew.okk.product.repository.ProductRepository;

// week 5, pattern command 3 Jakub Marciniuk
public class AddProductCommand implements ProductCommand{
    private final ProductRepository productRepository;
    private final BaseProduct product;
    private String assignedId;

    public AddProductCommand(ProductRepository productRepository, BaseProduct product) {
        this.productRepository = productRepository;
        this.product = product;
    }

    @Override
    public void execute() {
        productRepository.addProduct(product);
        assignedId = productRepository.getIdByProduct(product);
    }

    @Override
    public void undo() {
        if (assignedId != null) {
            productRepository.removeProduct(assignedId);
            assignedId = null;
        }
    }
}
// end of week 5, pattern command 3
