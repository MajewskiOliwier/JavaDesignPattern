package allnew.okk.shop.factory;

import allnew.okk.shop.model.OnlineShop;
import allnew.okk.shop.model.PhysicalShop;
import lombok.Getter;

// Week 9 - Maintaining Clean Code Principles

// Week 2, Pattern Singleton 3
// Implement the factory as a Singleton.
// Provides a single central point for creating objects in the application.
public class ShopFactory {

    @Getter
    private static final ShopFactory instance = new ShopFactory();

    private ShopFactory() {}
    // End, Week 2, Singleton Pattern 3

    // Week 2, Factory Pattern 1
    // Simple Factory pattern with parameters, initializes objects via Builders.
    // Allows you to create different types of stores (Online/Physical) using readable methods.

    // Factory accepting parameters for an online store
    public OnlineShop createOnlineShop(String name, String description, String websiteUrl) {
        validateCommonInputs(name, description);
        validateSpecificInput(websiteUrl, "Adres URL witryny");

        return new OnlineShop.Builder()
                .setName(name)
                .setDescription(description)
                .setWebsiteUrl(websiteUrl)
                .build();
    }

    // Factory accepting parameters for a stationary store
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