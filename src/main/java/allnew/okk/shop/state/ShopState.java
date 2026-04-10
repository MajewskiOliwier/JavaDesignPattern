package allnew.okk.shop.state;

// Week 6, Pattern State 1
// Interface defining the behavior associated with a particular state of the shop.
public interface ShopState {
    boolean canAcceptOrders();
    String getStateName();
}
// End Week 6, Pattern State 1