package allnew.okk.shop.proxy;

import allnew.okk.shop.model.BaseShop;
import allnew.okk.shop.repository.ShopRepository;

import java.util.List;

// Week 4, Pattern Proxy 1
// This class serves as a proxy to the ShopRepository.
// It adds caching functionality to improve performance when retrieving the list of all shops.
public class ShopRepositoryProxy {

    private final ShopRepository realRepository;

    private List<BaseShop> cachedShops = null;

    public ShopRepositoryProxy() {
        this.realRepository = ShopRepository.getInstance();
    }

    public List<BaseShop> getAllShops() {
        if (cachedShops == null) {
            System.out.println("[SHOP PROXY] Fetching data from the real repository...");
            cachedShops = realRepository.getAllShops();
        } else {
            System.out.println("[SHOP PROXY] Returning data from cache...");
        }
        return cachedShops;
    }

    public void addShop(BaseShop shop) {
        realRepository.addShop(shop);
        invalidateCache();
    }

    public BaseShop getShop(String id) {
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