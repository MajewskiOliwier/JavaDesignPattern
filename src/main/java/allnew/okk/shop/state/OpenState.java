package allnew.okk.shop.state;

// Week 6, Pattern State 2
// Represents a shop that is currently open and operating normally.
public class OpenState implements ShopState {

    @Override
    public boolean canAcceptOrders() {
        return true; // The shop can process orders normally
    }

    @Override
    public String getStateName() {
        return "OPEN";
    }
}
// End Week 6, Pattern State 2