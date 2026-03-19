package allnew.okk.product.flyweight;

import allnew.okk.product.model.ProductCondition;

import java.util.HashMap;
import java.util.Map;

// Week 3, Pattern Flyweight 2 Jakub Marciniuk
// This class serves as a registry for ProductConditionFlyweight instances, ensuring that only one instance exists
public class ProductConditionRegistry {
    private static final Map<ProductCondition, ProductConditionFlyweight> registry = new HashMap<>();

    public static ProductConditionFlyweight get(ProductCondition condition) {
        if(!registry.containsKey(condition)) {
            registry.put(condition, new ProductConditionFlyweight(condition));
        }
        return registry.get(condition);
    }
}
// End Week 3, Pattern Flyweight 2 Jakub Marciniuk