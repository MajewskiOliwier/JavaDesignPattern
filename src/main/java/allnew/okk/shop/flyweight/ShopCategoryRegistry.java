package allnew.okk.shop.flyweight;

import java.util.HashMap;
import java.util.Map;

// Week 7 - Open-Closed Principle (OCP)
// Week 4, Pattern Flyweight 3
// Registry acts as a Flyweight Factory. It caches the flyweights
// and ensures only one instance of a specific category exists in memory.
public class ShopCategoryRegistry {
    private static final Map<ShopCategory, ShopCategoryFlyweight> cache = new HashMap<>();

    // Sterowanie danymi - Mapa pełniąca rolę dynamicznej konfiguracji
    private static final Map<ShopCategory, CategoryData> dataConfig = new HashMap<>();

    // Klasa pomocnicza
    private static class CategoryData {
        String iconUrl;
        String guidelines;
        CategoryData(String iconUrl, String guidelines) {
            this.iconUrl = iconUrl;
            this.guidelines = guidelines;
        }
    }

    static {
        registerCategoryData(ShopCategory.ELECTRONICS, "electronics.png", "Strict 24-month warranty required.");
        registerCategoryData(ShopCategory.CLOTHING, "clothing.png", "Must provide a 30-day return policy.");
        registerCategoryData(ShopCategory.GROCERY, "grocery.png", "Strict adherence to food safety.");
        registerCategoryData(ShopCategory.BOOKSTORE, "books.png", "Standard shipping rates apply.");
    }

    public static void registerCategoryData(ShopCategory category, String iconUrl, String guidelines) {
        dataConfig.put(category, new CategoryData(iconUrl, guidelines));
    }

    public static ShopCategoryFlyweight get(ShopCategory category) {
        if (!cache.containsKey(category)) {
            CategoryData data = dataConfig.getOrDefault(category, new CategoryData("default.png", "No guidelines."));
            cache.put(category, new ShopCategoryFlyweight(category, data.iconUrl, data.guidelines));
        }
        return cache.get(category);
    }
}
// End Week 4, Pattern Flyweight 3