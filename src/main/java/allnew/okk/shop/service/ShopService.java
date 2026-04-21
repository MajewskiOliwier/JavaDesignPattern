package allnew.okk.shop.service;

import allnew.okk.shop.model.BaseShop;
import allnew.okk.shop.repository.ShopRepository;
// Week 9 - Maintaining Clean Code Principles
// Week 2, Pattern Singleton 2
// Implementacja wzorca Lazy Singleton z mechanizmem Double-Checked Locking.
// Zapewnia to bezpieczeństwo wielowątkowe i oszczędność zasobów poprzez tworzenie obiektu dopiero przy pierwszym użyciu.
public class ShopService {

    private static final String DUPLICATE_SUFFIX = " (Filia)";

    // Instancja volatile dla bezpeiczeństwa dla zapewnienia widoczności zmian zmiennych pomiędzy różnymi wątkami
    private static volatile ShopService instance;

    private final ShopRepository shopRepository;

    // Konstruktor
    private ShopService() {
        this.shopRepository = ShopRepository.getInstance();
    }

    // Globalna metoda dostępowa z podwójnym sprawdzaniem
    public static ShopService getInstance() {
        if (instance == null) {
            synchronized (ShopService.class) { // gwarantuje, że tylko jeden wątek na raz może wykonywać kod wewnątrz tej sekcji
                if (instance == null) {
                    instance = new ShopService();
                }
            }
        }
        return instance;
    }
    // End Week 2, Pattern  Singleton 2

    // Week 2, Pattern  Prototype 4
    // Praktyczne zastosowanie wzorca Prototype do duplikowania obiektów sklepu.
    // Wykorzystuje metodę clone() do tworzenia kopii istniejącego obiektu bez wywoływania konstruktora.
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