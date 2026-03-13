package allnew.okk.shop.decorator;

// Tydzień 3, Wzorzec Decorator 1
// Interfejs bazowy określający sposób wyświetlania profilu sklepu.
// Jest on implementowany zarówno przez podstawowy sklep, jak i jego dekoratory.
public interface ShopDisplay {
    String getDisplayName();
    String getDisplayDescription();
}
// Koniec, Tydzień 3, Wzorzec Decorator 1