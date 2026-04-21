package allnew.okk.shop.proxy;

import allnew.okk.shop.model.BaseShop;
import allnew.okk.shop.repository.ShopRepository;

import java.util.List;
// Week 9 - Maintaining Clean Code Principles
// Week 4, Pattern Proxy 1
// This class serves as a proxy to the ShopRepository.
// It adds caching functionality to improve performance when retrieving the list of all shops.
public class ShopRepositoryProxy {

    private static final String LOG_FETCHING = "[PROXY SKLEPU] Pobieranie danych z rzeczywistego repozytorium...";
    private static final String LOG_CACHE = "[PROXY SKLEPU] Zwracanie danych z pamięci podręcznej...";

    private final ShopRepository realRepository;

    private List<BaseShop> cachedShops = null;

    public ShopRepositoryProxy() {
        this.realRepository = ShopRepository.getInstance();
    }

    public List<BaseShop> getAllShops() {
        if (cachedShops == null) {
            System.out.println(LOG_FETCHING);
            cachedShops = realRepository.getAllShops();
        } else {
            System.out.println(LOG_CACHE);
        }
        return cachedShops;
    }

    public void addShop(BaseShop shop) {
        if (shop == null) {
            throw new IllegalArgumentException("Nie można dodać pustego sklepu do proxy.");
        }
        realRepository.addShop(shop);
        invalidateCache();
    }

    public BaseShop getShop(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID sklepu nie może być puste.");
        }
        return realRepository.getShop(id);
    }

    public void clear() {
        realRepository.clear();
        invalidateCache();
    }

    public void invalidateCache() {
        cachedShops = null;
    }
}
// End Week 4, Pattern Proxy 1