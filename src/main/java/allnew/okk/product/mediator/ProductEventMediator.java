package allnew.okk.product.mediator;

import allnew.okk.product.model.BaseProduct;

// week 5, pattern mediator Jakub Marciniuk
public interface ProductEventMediator {
    void notify(BaseProduct product, ProductEvent event);
}
// end of week 5, pattern mediator
