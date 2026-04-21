package allnew.okk.shop.flyweight;

import lombok.Getter;

// Week 9 - Maintaining Clean Code Principles

// Week 7 - Open-Closed Principle (OCP)
// Week 4, Pattern Flyweight 2
// The Flyweight object containing heavy or repetitive data.
// It is shared among multiple shop instances to save RAM.
public class ShopCategoryFlyweight {
    private final ShopCategory category;
    @Getter
    private final String iconUrl;
    private final String guidelines;

    public ShopCategoryFlyweight(ShopCategory category, String iconUrl, String guidelines) {
        if (category == null || iconUrl == null || guidelines == null) {
            throw new IllegalArgumentException("Parametry konfiguracji kategorii nie mogą być puste.");
        }
        this.category = category;
        this.iconUrl = iconUrl;
        this.guidelines = guidelines;
    }

    public String getGuidelines() {
        return guidelines;
    }
}
// End Week 4, Pattern Flyweight 2