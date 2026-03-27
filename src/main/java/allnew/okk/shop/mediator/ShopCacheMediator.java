package allnew.okk.shop.mediator;

import allnew.okk.shop.model.BaseShop;
import allnew.okk.shop.proxy.ShopRepositoryProxy;

// Week 5, Pattern Mediator 3
// Concrete mediator that listens to shop events and invalidates the cache
// in the ShopRepositoryProxy whenever a shop is modified.
public class ShopCacheMediator implements ShopEventMediator {
    private final ShopRepositoryProxy proxy;

    public ShopCacheMediator(ShopRepositoryProxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public void notify(BaseShop shop, ShopEvent event) {
        System.out.println("[MEDIATOR] Received event: " + event + " for shop: " + shop.getName());
        // When a shop is updated, we must clear the outdated cache
        proxy.invalidateCache();
    }
}
// End Week 5, Pattern Mediator 3