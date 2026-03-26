package allnew.okk.product.mediator;

import allnew.okk.product.model.BaseProduct;
import allnew.okk.product.proxy.ProductRepositoryProxy;

// week 5, pattern mediator 2 Jakub Marciniuk
public class ProductCacheMediator implements ProductEventMediator{
    private final ProductRepositoryProxy proxy;

    public ProductCacheMediator(ProductRepositoryProxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public void notify(BaseProduct product, ProductEvent event) {
        System.out.println("ProductCacheMediator received event: " + event + " for product: " + product.getName());
        proxy.invalidateCache();
    }
}
// end of week 5, pattern mediator 2