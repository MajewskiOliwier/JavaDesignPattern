package allnew.okk.shop.repository;

import allnew.okk.shop.model.BaseShop;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import allnew.okk.shop.interpreter.ShopSearchExpression;

// Week 2, Pattern  Singleton 1
    // Implementacja wzorca Eager Singleton, gdzie instancja tworzona jest natychmiast przy ładowaniu klasy.
    // Gwarantuje to dostępność obiektu od momentu uruchomienia aplikacji i prostotę implementacji.
public class ShopRepository {

    // Statyczna instancja tworzona jednocześnie z metodą dostępową
    @Getter
    private final static ShopRepository instance = new ShopRepository();

    // Wewnętrzna baza sklepów
    private final Map<String, BaseShop> shops = new HashMap<>();
    private int nextId = 1;

    // Prywatny konstruktor - blokuje możliwość stworzenia drugiej instancji repozytorium
    private ShopRepository() {}


    public void addShop(BaseShop shop) {
        String id = "SHOP-" + nextId++;
        shops.put(id, shop);
    }

    public BaseShop getShop(String id) {
        return shops.get(id);
    }

    public List<BaseShop> getAllShops() {
        return new ArrayList<>(shops.values());
    }

    // metoda restująca pamięć repozytorium
    public void clear() {
        shops.clear();
        nextId = 1;
    }

    // Week 5, Pattern Interpreter 5
    // Applies the interpreter pattern to filter shops based on complex logical expressions.
    public List<BaseShop> filter(ShopSearchExpression expression) {
        return shops.values().stream()
                .filter(expression::evaluate)
                .collect(Collectors.toList());
    }
    // End Week 5, Pattern Interpreter 5
}

// End Week 2, Pattern  Singleton 1