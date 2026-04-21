package allnew.okk.shop.mediator;

import allnew.okk.shop.model.BaseShop;
import allnew.okk.shop.proxy.ShopRepositoryProxy;

// Week 9 - Maintaining Clean Code Principles

// Week 5, Pattern Mediator 3
// Concrete mediator that listens to shop events and invalidates the cache
// in the ShopRepositoryProxy whenever a shop is modified.
public class ShopCacheMediator implements ShopEventMediator {

    private static final String LOG_PREFIX = "[MEDIATOR] Otrzymano zdarzenie: ";
    private static final String LOG_SUFFIX = " dla sklepu: ";

    private final ShopRepositoryProxy proxy;

    public ShopCacheMediator(ShopRepositoryProxy proxy) {
        if (proxy == null) {
            throw new IllegalArgumentException("Proxy repozytorium nie może być puste.");
        }
        this.proxy = proxy;
    }

    @Override
    public void notify(BaseShop shop, ShopEvent event) {
        validateEventData(shop, event);
        logEvent(shop, event);
        proxy.invalidateCache();
    }

    private void validateEventData(BaseShop shop, ShopEvent event) {
        if (shop == null) {
            throw new IllegalArgumentException("Sklep docelowy zdarzenia nie może być pusty.");
        }
        if (event == null) {
            throw new IllegalArgumentException("Typ zdarzenia nie może być pusty.");
        }
    }

    private void logEvent(BaseShop shop, ShopEvent event) {
        System.out.println(LOG_PREFIX + event + LOG_SUFFIX + shop.getName());
    }
}
// End Week 5, Pattern Mediator 3