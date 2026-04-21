package allnew.okk.shop.state;
// Week 9 - Maintaining Clean Code Principles
// Week 6, Pattern State 3
// Represents a shop that is temporarily closed (e.g., outside business hours or on holidays).
public class ClosedState implements ShopState {

    private static final String STATE_NAME = "CLOSED";
    private static final String LOG_CLOSED = "[STAN] Sklep jest obecnie zamknięty. Nie można przyjmować zamówień.";

    @Override
    public boolean canAcceptOrders() {
        System.out.println(LOG_CLOSED);
        return false;
    }

    @Override
    public String getStateName() {
        return STATE_NAME;
    }
}
// End Week 6, Pattern State 3