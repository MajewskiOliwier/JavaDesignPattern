package allnew.okk.product.state;

// Week 6, pattern state Marciniuk
public class DiscontinuedState implements ProductState {
    @Override
    public boolean canBePurchased() {
        return false;
    }

    @Override
    public String getStateName() {
        return "Discontinued";
    }
}
// End Week 6, pattern state Marciniuk