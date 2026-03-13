package allnew.okk.shop.decorator;

// Week 3, Decorator Pattern 1
// Base interface defining how a shop profile is displayed.
// It is implemented by both the base shop and its decorators.
public interface ShopDisplay {
    String getDisplayName();
    String getDisplayDescription();
}
// End of Week 3, Decorator Pattern 1