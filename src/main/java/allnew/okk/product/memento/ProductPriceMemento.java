package allnew.okk.product.memento;

import java.time.LocalDateTime;

// week 5, pattern memento Jakub Marciniuk
public class ProductPriceMemento {
    private final double price;
    private final LocalDateTime timestamp;

    public ProductPriceMemento(double price) {
        this.price = price;
        this.timestamp = LocalDateTime.now();
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "ProductPriceMemento{" +
                "price=" + price +
                ", timestamp=" + timestamp +
                '}';
    }

}
// end of week 5, pattern memento, jakub marciniuk