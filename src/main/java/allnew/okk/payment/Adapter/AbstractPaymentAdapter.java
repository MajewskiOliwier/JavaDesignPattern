package allnew.okk.payment.Adapter;

// Week 8 Dependency Inversion Oliwier
public abstract class AbstractPaymentAdapter implements PaymentGateway {
    protected String lastTransactionID;

    // shared logic for derived classes
    protected void saveTransactionID(String id) {
        this.lastTransactionID = id;
    }

    public String getLastTransactionID() {
        return lastTransactionID;
    }
}
//End Week 8 Dependency Inversion Oliwier