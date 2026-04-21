package allnew.okk.shop.factory;

import allnew.okk.shop.model.OnlineShop;
import allnew.okk.shop.model.PhysicalShop;
import lombok.Getter;

// Week 9 - Maintaining Clean Code Principles

// Week 2, Pattern Singleton 3
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
        validateCommonInputs(name, description);
        validateSpecificInput(websiteUrl, "Adres URL witryny");

        return new OnlineShop.Builder()
                .setName(name)
                .setDescription(description)
                .setWebsiteUrl(websiteUrl)
                .build();
    }

    // Fabryka przyjmująca parametry dla sklepu stacjonarnego
    public PhysicalShop createPhysicalShop(String name, String description, String address) {
        validateCommonInputs(name, description);
        validateSpecificInput(address, "Adres fizyczny sklepu");

        return new PhysicalShop.Builder()
                .setName(name)
                .setDescription(description)
                .setAddress(address)
                .build();
    }

    private void validateCommonInputs(String name, String description) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nazwa sklepu nie może być pusta.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Opis sklepu nie może być pusty.");
        }
    }

    private void validateSpecificInput(String input, String fieldName) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " nie może być pusty.");
        }
    }
    // End Week 2, Pattern Factory 1
}