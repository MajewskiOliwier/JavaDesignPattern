package allnew.okk.shop.flyweight;

import java.util.HashMap;
import java.util.Map;

// Week 4, Pattern Flyweight 3
// Registry acts as a Flyweight Factory. It caches the flyweights
// and ensures only one instance of a specific category exists in memory.
public class ShopCategoryRegistry {
    private static final Map<ShopCategory, ShopCategoryFlyweight> cache = new HashMap<>();

    public static ShopCategoryFlyweight get(ShopCategory category) {
        if (!cache.containsKey(category)) {
            cache.put(category, new ShopCategoryFlyweight(category));
        }
        return cache.get(category);
    }
}
// End Week 4, Pattern Flyweight 3