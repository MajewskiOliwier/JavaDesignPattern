package allnew.okk.shop.isp;

// Week 8 - Interface Segregation Principle 2 (Wydzielony interfejs 1)
public interface ProductManagement {
    void listProduct(String productName, double price);
    void updateProductStock(String productId, int quantity);
}