package allnew.okk.shop.isp;

// Week 8 - Interface Segregation Principle 5 (Wydzielony interfejs 4)
public interface CustomerService {
    void respondToCustomerQuestion(String questionId, String answer);
    void openDisputeWithBuyer(String orderId, String reason);
}