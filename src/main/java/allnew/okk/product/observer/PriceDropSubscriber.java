package allnew.okk.product.observer;

// Week 6, Pattern Observer Marciniuk
// powiadamia klienta o zmianie cen
public class PriceDropSubscriber implements ProductObserver {
    private final String customerEmail;

    public PriceDropSubscriber(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public void onPriceChanged(String productName, double oldPrice, double newPrice) {
        if (newPrice < oldPrice) {
            System.out.println("Notification to " + customerEmail + ": The price of " + productName + " has dropped from " + oldPrice + " to " + newPrice + "!");
        }
    }
}
// End Week 6, Pattern Observer Marciniuk
