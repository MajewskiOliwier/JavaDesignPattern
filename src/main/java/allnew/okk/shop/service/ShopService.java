package allnew.okk.shop.service;

import allnew.okk.shop.model.BaseShop;
import allnew.okk.shop.repository.ShopRepository;
// Week 9 - Maintaining Clean Code Principles
// Week 2, Pattern Singleton 2
// Implementation of the Lazy Singleton pattern with Double-Checked Locking.
// This provides multithreading safety and resource savings by only creating an object on first use.
public class ShopService {

    private static final String DUPLICATE_SUFFIX = " (Filia)";

    // Volatile instance for safety to ensure visibility of variable changes between different threads
    private static volatile ShopService instance;
    private final ShopRepository shopRepository;

    // Constructor
    private ShopService() {
        this.shopRepository = ShopRepository.getInstance();
    }

    // Global access method with check
    public static ShopService getInstance() {
        if (instance == null) {
            synchronized (ShopService.class) { // guarantees that only one thread at a time can execute code inside this section
                if (instance == null) {
                    instance = new ShopService();
                }
            }
        }
        return instance;
    }
    // End Week 2, Pattern  Singleton 2

    // Week 2, Pattern  Prototype 4
    // A practical application of the Prototype pattern for duplicating store objects.
    // Uses the clone() method to create a copy of an existing object without calling a constructor.
    public void duplicateShop(String shopId) {
        validateShopId(shopId);

        BaseShop originalShop = fetchOriginalShop(shopId);
        BaseShop duplicatedShop = createDuplicate(originalShop);

        shopRepository.addShop(duplicatedShop);
    }

    private void validateShopId(String shopId) {
        if (shopId == null || shopId.trim().isEmpty()) {
            throw new IllegalArgumentException("ID sklepu do duplikacji nie może być puste.");
        }
    }

    private BaseShop fetchOriginalShop(String shopId) {
        return shopRepository.getShop(shopId);
    }

    private BaseShop createDuplicate(BaseShop originalShop) {
        BaseShop duplicatedShop = originalShop.clone();
        String newName = originalShop.getName() + DUPLICATE_SUFFIX;
        duplicatedShop.setName(newName);
        return duplicatedShop;
    }
}