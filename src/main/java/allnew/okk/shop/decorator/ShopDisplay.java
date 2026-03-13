package allnew.okk.shop.decorator;

// Week 3, Pattern Decorator 1
// Interfejs bazowy określający sposób wyświetlania profilu sklepu.
// Jest on implementowany zarówno przez podstawowy sklep, jak i jego dekoratory.
public interface ShopDisplay {
    String getDisplayName();
    String getDisplayDescription();
}
// End Week 3, Pattern Decorator 1