package allnew.okk.shop.repository;

import allnew.okk.shop.model.BaseShop;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import allnew.okk.shop.interpreter.ShopSearchExpression;
// Week 9 - Maintaining Clean Code Principles
// Week 2, Pattern  Singleton 1
// Implementation of the Eager Singleton pattern, where an instance is created immediately upon class loading.
// This ensures object availability from the moment the application starts and simplifies implementation.
public class ShopRepository {

    private static final String ID_PREFIX = "SHOP-";
    private static final int INITIAL_ID = 1;

    // Static instance created simultaneously with the accessor method
     @Getter
    private final static ShopRepository instance = new ShopRepository();

    // Internal store database
    private final Map<String, BaseShop> shops = new HashMap<>();
    private int nextId = INITIAL_ID;

    // Private constructor - blocks the ability to create a second instance of the repository
    private ShopRepository() {}

    public void addShop(BaseShop shop) {
        if (shop == null) {
            throw new IllegalArgumentException("Nie można dodać pustego sklepu do repozytorium.");
        }
        String id = generateNextId();
        shops.put(id, shop);
    }

    private String generateNextId() {
        return ID_PREFIX + nextId++;
    }

    public BaseShop getShop(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID sklepu nie może być puste.");
        }

        BaseShop shop = shops.get(id);
        if (shop == null) {
            throw new IllegalArgumentException("Nie znaleziono sklepu o podanym ID: " + id);
        }

        return shop;
    }

    public List<BaseShop> getAllShops() {
        return new ArrayList<>(shops.values());
    }

    // metoda restująca pamięć repozytorium
    public void clear() {
        shops.clear();
        nextId = INITIAL_ID;
    }

    // Week 5, Pattern Interpreter 5
    // Applies the interpreter pattern to filter shops based on complex logical expressions.
    public List<BaseShop> filter(ShopSearchExpression expression) {
        if (expression == null) {
            throw new IllegalArgumentException("Wyrażenie filtrujące nie może być puste.");
        }
        return shops.values().stream()
                .filter(expression::evaluate)
                .collect(Collectors.toList());
    }
    // End Week 5, Pattern Interpreter 5
}
// End Week 2, Pattern  Singleton 1