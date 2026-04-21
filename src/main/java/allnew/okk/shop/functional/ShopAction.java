package allnew.okk.shop.functional;

import allnew.okk.shop.model.BaseShop;

// Week 10 - Functional Interface
@FunctionalInterface
public interface ShopAction {
    void execute(BaseShop shop);
}