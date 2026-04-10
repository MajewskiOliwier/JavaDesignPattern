package allnew.okk.product.state;

// Week 6, pattern state Marciniuk
public class OutOfStockState implements ProductState {
    @Override
    public boolean canBePurchased() {
        return false;
    }

    @Override
    public String getStateName() {
        return "Out of Stock";
    }
}
// End Week 6, pattern state Marciniuk