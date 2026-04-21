package allnew.okk.shop.state;

// Week 6, Pattern State 2
// Represents a shop that is currently open and operating normally.
public class OpenState implements ShopState {

    private static final String STATE_NAME = "OPEN";

    @Override
    public boolean canAcceptOrders() {
        return true;
    }

    @Override
    public String getStateName() {
        return STATE_NAME;
    }
}
// End Week 6, Pattern State 2