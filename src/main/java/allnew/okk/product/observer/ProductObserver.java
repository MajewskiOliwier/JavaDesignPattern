package allnew.okk.product.observer;

// Week 6, Pattern Observer Marciniuk
// Interfejs obserwatora dla produktów, umożliwiający reagowanie na zmiany cen produktów
public interface ProductObserver {
    void onPriceChanged(String productName, double oldPrice, double newPrice);
}
// End Week 6, Pattern Observer Marciniuk