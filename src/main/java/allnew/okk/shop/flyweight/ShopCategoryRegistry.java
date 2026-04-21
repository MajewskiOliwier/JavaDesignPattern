package allnew.okk.shop.flyweight;

import java.util.HashMap;
import java.util.Map;

// Week 9 - Maintaining Clean Code Principles

// Week 7 - Open-Closed Principle (OCP)
// Week 4, Pattern Flyweight 3
// Registry acts as a Flyweight Factory. It caches the flyweights
// and ensures only one instance of a specific category exists in memory.
public class ShopCategoryRegistry {

    private static final String DEFAULT_ICON = "default.png";
    private static final String DEFAULT_GUIDELINES = "No guidelines.";

    private static final String ICON_ELEC = "electronics.png";
    private static final String GUIDELINES_ELEC = "Strict 24-month warranty required.";

    private static final String ICON_CLOTH = "clothing.png";
    private static final String GUIDELINES_CLOTH = "Must provide a 30-day return policy.";

    private static final String ICON_GROCERY = "grocery.png";
    private static final String GUIDELINES_GROCERY = "Strict adherence to food safety.";

    private static final String ICON_BOOKS = "books.png";
    private static final String GUIDELINES_BOOKS = "Standard shipping rates apply.";

    private static final Map<ShopCategory, ShopCategoryFlyweight> cache = new HashMap<>();
    private static final Map<ShopCategory, CategoryData> dataConfig = new HashMap<>();

    private static class CategoryData {
        final String iconUrl;
        final String guidelines;

        CategoryData(String iconUrl, String guidelines) {
            this.iconUrl = iconUrl;
            this.guidelines = guidelines;
        }
    }

    static {
        initializeDefaultCategories();
    }

    private static void initializeDefaultCategories() {
        registerCategoryData(ShopCategory.ELECTRONICS, ICON_ELEC, GUIDELINES_ELEC);
        registerCategoryData(ShopCategory.CLOTHING, ICON_CLOTH, GUIDELINES_CLOTH);
        registerCategoryData(ShopCategory.GROCERY, ICON_GROCERY, GUIDELINES_GROCERY);
        registerCategoryData(ShopCategory.BOOKSTORE, ICON_BOOKS, GUIDELINES_BOOKS);
    }

    public static void registerCategoryData(ShopCategory category, String iconUrl, String guidelines) {
        if (category == null) {
            throw new IllegalArgumentException("Kategoria nie może być pusta.");
        }
        dataConfig.put(category, new CategoryData(iconUrl, guidelines));
    }

    public static ShopCategoryFlyweight get(ShopCategory category) {
        if (category == null) {
            throw new IllegalArgumentException("Kategoria nie może być pusta.");
        }

        if (!cache.containsKey(category)) {
            ShopCategoryFlyweight newFlyweight = createFlyweight(category);
            cache.put(category, newFlyweight);
        }
        return cache.get(category);
    }

    private static ShopCategoryFlyweight createFlyweight(ShopCategory category) {
        CategoryData data = dataConfig.getOrDefault(category, new CategoryData(DEFAULT_ICON, DEFAULT_GUIDELINES));
        return new ShopCategoryFlyweight(category, data.iconUrl, data.guidelines);
    }
}
// End Week 4, Pattern Flyweight 3