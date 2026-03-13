package allnew.okk.shop.decorator;

// Week 3, Decorator Pattern 3
// Abstract decorator class. Implements the same interface as BaseShop.
// Stores an instance of the "wrapped" object and delegates all calls to it by default.
public abstract class ShopDecorator implements ShopDisplay {
    protected ShopDisplay wrappedShop;

    public ShopDecorator(ShopDisplay wrappedShop) {
        this.wrappedShop = wrappedShop;
    }

    @Override
    public String getDisplayName() {
        return wrappedShop.getDisplayName();
    }

    @Override
    public String getDisplayDescription() {
        return wrappedShop.getDisplayDescription();
    }
}
// End of Week 3, Decorator Pattern 3