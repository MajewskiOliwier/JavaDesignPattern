package allnew.okk.shop.state;
// Week 9 - Maintaining Clean Code Principles
// Week 6, Pattern State 4
// Represents a shop that has been suspended by administrators (e.g., policy violation).
public class SuspendedState implements ShopState {

    private static final String STATE_NAME = "SUSPENDED";
    private static final String LOG_SUSPENDED = "[STAN] Sklep jest zawieszony. Wszystkie operacje są zablokowane.";

    @Override
    public boolean canAcceptOrders() {
        System.out.println(LOG_SUSPENDED);
        return false;
    }

    @Override
    public String getStateName() {
        return STATE_NAME;
    }
}
// End Week 6, Pattern State 4