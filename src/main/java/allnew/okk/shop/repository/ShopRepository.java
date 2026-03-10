package allnew.okk.shop.repository;

import allnew.okk.shop.model.BaseShop;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Wzorzec EAGER SINGLETON  - ładowany od początku uruchomienia aplikacji
public class ShopRepository {

    // Statyczna instancja tworzona od razu przy ładowaniu klasy do pamięci jednocześnie z metodą dostępową
    @Getter
    private final static ShopRepository instance = new ShopRepository();

    // Wewnętrzna baza sklepów
    private final Map<String, BaseShop> shops = new HashMap<>();
    private int nextId = 1;

    // Prywatny konstruktor - blokuje możliwość użycia "new" na zewnątrz
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
}