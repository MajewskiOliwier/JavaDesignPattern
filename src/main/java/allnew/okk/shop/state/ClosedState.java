package allnew.okk.shop.state;

// Week 6, Pattern State 3
// Represents a shop that is temporarily closed (e.g., outside business hours or on holidays).
public class ClosedState implements ShopState {

    @Override
    public boolean canAcceptOrders() {
        System.out.println("State: Shop is currently closed. Cannot accept orders.");
        return false;
    }

    @Override
    public String getStateName() {
        return "CLOSED";
    }
}
// End Week 6, Pattern State 3