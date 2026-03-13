package allnew.okk.shop.decorator;

// Tydzień 3, Wzorzec Decorator 3
// Abstrakcyjna klasa dekoratora. Implementuje ten sam interfejs co BaseShop.
// Przechowuje instancję "owiniętego" obiektu i domyślnie deleguje do niego wszystkie wywołania.
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
// Koniec, Tydzień 3, Wzorzec Decorator 3