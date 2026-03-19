package allnew.okk.shop.facade;

import allnew.okk.shop.bridge.EmailNotificationSender;
import allnew.okk.shop.bridge.SmsNotificationSender;
import allnew.okk.shop.decorator.PremiumBadgeDecorator;
import allnew.okk.shop.decorator.ShopDisplay;
import allnew.okk.shop.decorator.VerifiedBadgeDecorator;
import allnew.okk.shop.factory.ShopFactory;
import allnew.okk.shop.model.OnlineShop;
import allnew.okk.shop.model.PhysicalShop;
import allnew.okk.shop.repository.ShopRepository;

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
        OnlineShop shop = factory.createOnlineShop(name, description, url);

        shop.setNotificationSender(new EmailNotificationSender());

        repository.addShop(shop);
    }

    public ShopDisplay registerPremiumPhysicalShop(String name, String description, String address) {
        PhysicalShop shop = factory.createPhysicalShop(name, description, address);

        shop.setNotificationSender(new SmsNotificationSender());

        repository.addShop(shop);

        ShopDisplay decoratedShop = new VerifiedBadgeDecorator(shop);
        decoratedShop = new PremiumBadgeDecorator(decoratedShop);

        return decoratedShop;
    }
}
// End Week 4, Pattern Facade 1