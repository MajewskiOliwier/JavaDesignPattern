package allnew.okk.Currency.Flyweight;

import java.util.HashMap;
import java.util.Map;

// Week 4, Pattern Flyweight 3 Oliwier Majewski
// Registry holds the cache and shares instances
public class CurrencyRegistry {
    private static final Map<CurrencyType, CurrencyFlyweight> cache = new HashMap<>();

    public static CurrencyFlyweight get(CurrencyType type) {
        if (!cache.containsKey(type)) {
            cache.put(type, new CurrencyFlyweight(type));
        }
        return cache.get(type);
    }
}
// End Week 4, Pattern Flyweight 3 Oliwier Majewski