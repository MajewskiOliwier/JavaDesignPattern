package allnew.okk.shop.service;

import allnew.okk.shop.composite.ShopComponent;
import allnew.okk.shop.functional.ShopAction;
import allnew.okk.shop.memento.ShopProfileMemento;
import allnew.okk.shop.model.BaseShop;

import java.util.List;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ShopAnalyticsService {


    // Week 10 - Predicate and Stream processing of Collection 1 (List of Shops)
    public List<BaseShop> filterShops(List<BaseShop> shops, Predicate<BaseShop> criteria) {
        validateShopList(shops);
        validatePredicate(criteria);
        return applyFilterStream(shops, criteria);
    }

    // Week 10 - Function usage
    public List<String> extractShopNames(List<BaseShop> shops, Function<BaseShop, String> extractor) {
        validateShopList(shops);
        validateFunction(extractor);
        return applyExtractionStream(shops, extractor);
    }

    // Week 10 - Custom Functional Interface used in a stream
    public void executeActionOnShops(List<BaseShop> shops, ShopAction action) {
        validateShopList(shops);
        validateAction(action);
        applyActionStream(shops, action);
    }

    // Week 10 - Stream processing of Collection 2 (Memento History Stack)
    public List<String> getHistoryNames(Stack<ShopProfileMemento> history) {
        validateHistoryStack(history);
        return applyHistoryStream(history);
    }

    // Week 10 - Stream processing of Collection 3 (Composite Component List)
    public long countNetworkComponents(List<ShopComponent> components) {
        validateComponentList(components);
        return applyCountStream(components);
    }


    private List<BaseShop> applyFilterStream(List<BaseShop> shops, Predicate<BaseShop> criteria) {
        return shops.stream()
                .filter(criteria)
                .collect(Collectors.toList());
    }

    private List<String> applyExtractionStream(List<BaseShop> shops, Function<BaseShop, String> extractor) {
        return shops.stream()
                .map(extractor)
                .collect(Collectors.toList());
    }

    private void applyActionStream(List<BaseShop> shops, ShopAction action) {
        shops.forEach(action::execute);
    }

    private List<String> applyHistoryStream(Stack<ShopProfileMemento> history) {
        return history.stream()
                .map(ShopProfileMemento::getName)
                .collect(Collectors.toList());
    }

    private long applyCountStream(List<ShopComponent> components) {
        return components.size();
    }


    private void validateShopList(List<BaseShop> shops) {
        if (shops == null) {
            throw new IllegalArgumentException("The shop list cannot be null.");
        }
    }

    private void validateComponentList(List<ShopComponent> components) {
        if (components == null) {
            throw new IllegalArgumentException("The component list cannot be null.");
        }
    }

    private void validateHistoryStack(Stack<ShopProfileMemento> history) {
        if (history == null) {
            throw new IllegalArgumentException("The history stack cannot be null.");
        }
    }

    private void validatePredicate(Predicate<BaseShop> criteria) {
        if (criteria == null) {
            throw new IllegalArgumentException("The filter criteria cannot be null.");
        }
    }

    private void validateFunction(Function<BaseShop, String> extractor) {
        if (extractor == null) {
            throw new IllegalArgumentException("The extractor function cannot be null.");
        }
    }

    private void validateAction(ShopAction action) {
        if (action == null) {
            throw new IllegalArgumentException("The shop action cannot be null.");
        }
    }
}