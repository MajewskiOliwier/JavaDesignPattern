package allnew.okk.shop.factory;

import allnew.okk.shop.model.OnlineShop;
import allnew.okk.shop.model.PhysicalShop;
import lombok.Getter;


// Week 2, Singleton Pattern 3
// Implementation of the factory as a Singleton.
// Ensures a single central point for object creation in the application.
public class ShopFactory {

    @Getter
    private static final ShopFactory instance = new ShopFactory();

    private ShopFactory() {}
    // End of Week 2, Singleton Pattern 3

    // Week 2, Factory Pattern 1
    // Simple Factory pattern with parameters, initializes objects through Builders.
    // Allows for creating different types of shops (Online/Physical) using readable methods.

    // Factory accepting parameters for an online shop
    public OnlineShop createOnlineShop(String name, String description, String websiteUrl) {
        return new OnlineShop.Builder()
                .setName(name)
                .setDescription(description)
                .setWebsiteUrl(websiteUrl)
                .build();
    }

    // Factory accepting parameters for a physical shop
    public PhysicalShop createPhysicalShop(String name, String description, String address) {
        return new PhysicalShop.Builder()
                .setName(name)
                .setDescription(description)
                .setAddress(address)
                .build();
    }
    // End of Week 2, Factory Pattern 1

}