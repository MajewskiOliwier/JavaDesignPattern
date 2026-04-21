package allnew.okk.shop.facade;

import allnew.okk.shop.bridge.EmailNotificationSender;
import allnew.okk.shop.bridge.SmsNotificationSender;
import allnew.okk.shop.decorator.PremiumBadgeDecorator;
import allnew.okk.shop.decorator.ShopDisplay;
import allnew.okk.shop.decorator.VerifiedBadgeDecorator;
import allnew.okk.shop.factory.ShopFactory;
import allnew.okk.shop.model.BaseShop;
import allnew.okk.shop.model.OnlineShop;
import allnew.okk.shop.model.PhysicalShop;
import allnew.okk.shop.repository.ShopRepository;

// Week 9 - Maintaining Clean Code Principles

// Week 4, Pattern Facade 1
// Facade hiding the complexity of creating and configuring shops.
// Coordinates the cooperation of previously made classes.
public class ShopManagementFacade {

    private final ShopRepository repository;
    private final ShopFactory factory;

    public ShopManagementFacade() {
        this.repository = ShopRepository.getInstance();
        this.factory = ShopFactory.getInstance();
    }

    public void registerStandardOnlineShop(String name, String description, String url) {
        validateShopInputs(name, description);
        OnlineShop shop = buildOnlineShop(name, description, url);
        configureEmailNotifications(shop);
        saveShop(shop);
    }

    public ShopDisplay registerPremiumPhysicalShop(String name, String description, String address) {
        validateShopInputs(name, description);
        PhysicalShop shop = buildPhysicalShop(name, description, address);
        configureSmsNotifications(shop);
        saveShop(shop);
        return decorateAsPremium(shop);
    }

    private void validateShopInputs(String name, String description) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nazwa sklepu nie może być pusta.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Opis sklepu nie może być pusty.");
        }
    }

    private OnlineShop buildOnlineShop(String name, String description, String url) {
        return factory.createOnlineShop(name, description, url);
    }

    private PhysicalShop buildPhysicalShop(String name, String description, String address) {
        return factory.createPhysicalShop(name, description, address);
    }

    private void configureEmailNotifications(BaseShop shop) {
        shop.setNotificationSender(new EmailNotificationSender());
    }

    private void configureSmsNotifications(BaseShop shop) {
        shop.setNotificationSender(new SmsNotificationSender());
    }

    private void saveShop(BaseShop shop) {
        repository.addShop(shop);
    }

    private ShopDisplay decorateAsPremium(ShopDisplay shop) {
        ShopDisplay verifiedShop = new VerifiedBadgeDecorator(shop);
        return new PremiumBadgeDecorator(verifiedShop);
    }
}
// End Week 4, Pattern Facade 1