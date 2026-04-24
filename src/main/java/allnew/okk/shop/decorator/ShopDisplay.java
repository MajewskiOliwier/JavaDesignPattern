package allnew.okk.shop.decorator;

// Week 3, Pattern Decorator 1
// A base interface that defines how the store profile is displayed.
// It is implemented by both the base store and its decorators.
public interface ShopDisplay {
    String getDisplayName();
    String getDisplayDescription();
}
// End Week 3, Pattern Decorator 1