package allnew.okk.shop.state;

// Week 6, Pattern State 4
// Represents a shop that has been suspended by administrators (e.g., policy violation).
public class SuspendedState implements ShopState {

    @Override
    public boolean canAcceptOrders() {
        System.out.println("State: Shop is suspended. All operations are blocked.");
        return false;
    }

    @Override
    public String getStateName() {
        return "SUSPENDED";
    }
}
// End Week 6, Pattern State 4