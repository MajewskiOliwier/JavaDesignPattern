package allnew.okk.product.state;

// Week 6, pattern state Marciniuk
public class AvailableState implements ProductState {
    @Override
    public boolean canBePurchased() {
        return true;
    }

    @Override
    public String getStateName() {
        return "Available";
    }
}
// End Week 6, pattern state Marciniuk