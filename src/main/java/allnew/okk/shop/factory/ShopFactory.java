package allnew.okk.shop.factory;

import allnew.okk.shop.model.OnlineShop;
import allnew.okk.shop.model.PhysicalShop;
import lombok.Getter;


//Week 2, Pattern Singleton 3
// Implementacja fabryki jako Singletona.
// Zapewnia jeden centralny punkt tworzenia obiektów w aplikacji.
public class ShopFactory {

    @Getter
    private static final ShopFactory instance = new ShopFactory();

    private ShopFactory() {}
    // Koniec, Tydzień 2, Wzorzec Singleton 3

    // Tydzień 2, Wzorzec Factory 1
    // Wzorzec Simple Factory z parametrami, inicjalizuje obiekty przez Buildery.
    // Pozwala na tworzenie różnych typów sklepów (Online/Physical) za pomocą czytelnych metod.

    // Fabryka przyjmująca parametry dla sklepu online
    public OnlineShop createOnlineShop(String name, String description, String websiteUrl) {
        return new OnlineShop.Builder()
                .setName(name)
                .setDescription(description)
                .setWebsiteUrl(websiteUrl)
                .build();
    }

    // Fabryka przyjmująca parametry dla sklepu stacjonarnego
    public PhysicalShop createPhysicalShop(String name, String description, String address) {
        return new PhysicalShop.Builder()
                .setName(name)
                .setDescription(description)
                .setAddress(address)
                .build();
    }
    // End Week 2, Pattern Factory 1

}