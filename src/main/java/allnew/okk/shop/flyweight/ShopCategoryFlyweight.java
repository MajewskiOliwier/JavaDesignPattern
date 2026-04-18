package allnew.okk.shop.flyweight;

// Week 7 - Open-Closed Principle (OCP)
// Week 4, Pattern Flyweight 2
// The Flyweight object containing heavy or repetitive data.
// It is shared among multiple shop instances to save RAM.
public class ShopCategoryFlyweight {
    private final ShopCategory category;
    private final String iconUrl;
    private final String guidelines;

    public ShopCategoryFlyweight(ShopCategory category, String iconUrl, String guidelines) {
        this.category = category;
        this.iconUrl = iconUrl;
        this.guidelines = guidelines;
    }

    public String getCategoryIconUrl() {
        return iconUrl;
    }

    public String getCategoryGuidelines() {
        return guidelines;
    }
}
// End Week 4, Pattern Flyweight 2