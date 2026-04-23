package allnew.okk.product.isp;
import allnew.okk.product.model.BaseProduct;

// Week 8, Interface Segregation Principle Marciniuk
public class SegregatedInterfaces {
    //Dostęp do bazy (Zamiast IProductManager)
    public interface IProductReader { BaseProduct getProduct(String id); }
    public interface IProductWriter { void addProduct(BaseProduct product); }

    //Operacje finansowe (Zamiast IFinancials)
    public interface ITaxable { double calculateTax(double price); }
    public interface IDiscountable { double applyDiscount(double price); }

    //Obserwatorzy zdarzeń (Zamiast IProductEvents)
    public interface IPriceDropObserver { void onPriceDropped(String productId, double newPrice); }
    public interface IStockObserver { void onStockChanged(String productId, int newQuantity); }
}
// End Week 8, Interface Segregation Principle Marciniuk