package allnew.okk.product.state;

// Week 8, Liskov Substitution Principle Marciniuk
public class ReservedState implements ProductState {
    @Override
    public boolean canBePurchased() {
        return false;
    }
    @Override
    public String getStateName() {
        return "Reserved";
    }
}

// End Week 8, Liskov Substitution Principle Marciniuk