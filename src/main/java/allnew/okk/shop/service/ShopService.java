package allnew.okk.shop.service;

import allnew.okk.shop.model.BaseShop;
import allnew.okk.shop.repository.ShopRepository;

// Wzorzec LAZY SINGLETON - ładowany dopiero wtedy, gdy jest używany
public class ShopService {

    // Instancja volatile dla bezpeiczeństwa
    private static volatile ShopService instance;

    private final ShopRepository shopRepository;

    // Konstruktor
    private ShopService() {
        this.shopRepository = ShopRepository.getInstance();
    }

    // Globalna metoda dostępowa z podwójnym sprawdzaniem
    public static ShopService getInstance() {
        if (instance == null) {
            synchronized (ShopService.class) {
                if (instance == null) {
                    instance = new ShopService();
                }
            }
        }
        return instance;
    }


    public void duplicateShop(String shopId) throws CloneNotSupportedException {
        BaseShop originalShop = shopRepository.getShop(shopId);
        if (originalShop != null) {
            BaseShop duplicatedShop = originalShop.clone();

            duplicatedShop.setName(originalShop.getName() + " (Filia)");

            shopRepository.addShop(duplicatedShop);
        }
    }
}