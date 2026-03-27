package allnew.okk.shop.mediator;

import allnew.okk.shop.model.BaseShop;

// Week 5, Pattern Mediator 2
// Interface for the Mediator. Components will use this to notify about events
// without knowing who exactly is going to react to them.
public interface ShopEventMediator {
    void notify(BaseShop shop, ShopEvent event);
}
// End Week 5, Pattern Mediator 2