package allnew.okk.shop.service;

import allnew.okk.shop.model.BaseShop;
import allnew.okk.shop.repository.ShopRepository;

// Tydzień 2, Wzorzec Singleton 2
// Implementacja wzorca Lazy Singleton z mechanizmem Double-Checked Locking.
// Zapewnia to bezpieczeństwo wielowątkowe i oszczędność zasobów poprzez tworzenie obiektu dopiero przy pierwszym użyciu.
public class ShopService {

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
    // Koniec, Tydzień 2, Wzorzec Singleton 2

    // Tydzień 2, Wzorzec Prototype 4
    // Praktyczne zastosowanie wzorca Prototype do duplikowania obiektów sklepu.
    // Wykorzystuje metodę clone() do tworzenia kopii istniejącego obiektu bez wywoływania konstruktora.
    public void duplicateShop(String shopId) throws CloneNotSupportedException {
        BaseShop originalShop = shopRepository.getShop(shopId);
        if (originalShop != null) {
            BaseShop duplicatedShop = originalShop.clone();
            duplicatedShop.setName(originalShop.getName() + " (Filia)");
            shopRepository.addShop(duplicatedShop);
        }
    }
}
