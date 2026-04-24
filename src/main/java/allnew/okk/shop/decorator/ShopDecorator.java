package allnew.okk.shop.decorator;
// Week 9 - Maintaining Clean Code Principles

// Week 8 - Liskov Substitution Principle 3 (Base Class)

// Week 3, Pattern Decorator 3
// An abstract decorator class. Implements the same interface as BaseShop.
// Stores an instance of the "wrapped" object and implicitly delegates all calls to it.
 public abstract class ShopDecorator implements ShopDisplay {
    protected final ShopDisplay wrappedShop;

    public ShopDecorator(ShopDisplay wrappedShop) {
        if (wrappedShop == null) {
            throw new IllegalArgumentException("Dekorowany sklep nie może być pusty.");
        }
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
// End Week 3, Pattern Decorator 3