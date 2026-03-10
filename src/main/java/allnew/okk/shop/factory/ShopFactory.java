package allnew.okk.shop.factory;

import allnew.okk.shop.model.OnlineShop;
import allnew.okk.shop.model.PhysicalShop;
import lombok.Getter;

public class ShopFactory {

    @Getter
    private static final ShopFactory instance = new ShopFactory();

    private ShopFactory() {}

    // Fabryka przyjmująca parametry dla sklepu internetowego
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
}