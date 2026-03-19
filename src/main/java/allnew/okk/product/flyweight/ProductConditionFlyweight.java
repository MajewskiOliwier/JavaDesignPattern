package allnew.okk.product.flyweight;

import allnew.okk.product.model.ProductCondition;

// Week 3, Pattern Flyweight 1 Jakub Marciniuk
// This class represents the Flyweight object for ProductCondition
public class ProductConditionFlyweight {
    private final ProductCondition condition;

    public ProductConditionFlyweight(ProductCondition condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return switch (condition) {
            case NONE -> "";
            case NEW -> "Brand new product, never used.";
            case USED -> "Used product, may have signs of wear.";
            case REFURBISHED -> "Refurbished product, restored to like-new condition.";
        };
    }

    public String getBadge() {
        return switch (condition) {
            case NONE -> "";
            case NEW -> "New";
            case USED -> "Used";
            case REFURBISHED -> "Refurbished";
        };
    }
}
// End Week 3, Pattern Flyweight 1 Jakub Marciniuk
