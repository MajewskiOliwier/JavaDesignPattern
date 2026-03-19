package allnew.okk.shop.flyweight;

// Week 4, Pattern Flyweight 2
// The Flyweight object containing heavy or repetitive data.
// It is shared among multiple shop instances to save RAM.
public class ShopCategoryFlyweight {
    private final ShopCategory category;

    public ShopCategoryFlyweight(ShopCategory category) {
        this.category = category;
    }

    public String getCategoryIconUrl() {
        return switch (category) {
            case ELECTRONICS -> "electronics.png";
            case CLOTHING -> "clothing.png";
            case GROCERY -> "grocery.png";
            case BOOKSTORE -> "books.png";
        };
    }

    public String getCategoryGuidelines() {
        return switch (category) {
            case ELECTRONICS -> "Strict 24-month warranty required for all electronic devices.";
            case CLOTHING -> "Must provide a 30-day return policy for unused clothing.";
            case GROCERY -> "Strict adherence to food safety and expiration date regulations.";
            case BOOKSTORE -> "Standard shipping rates apply to all printed media.";
        };
    }
}
// End Week 4, Pattern Flyweight 2