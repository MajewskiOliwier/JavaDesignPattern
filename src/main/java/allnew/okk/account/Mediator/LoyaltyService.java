package allnew.okk.account.Mediator;


//Week 5, Pattern Mediator 1 Oliwier Majewski
public class LoyaltyService {
    private static final double POINTS_PER_PLN = 0.5f;

    //This method adds Loyalty points to the class that implements LoyaltyEligible interface (as of now it is private account only)
    public void RewardCustomer(LoyaltyEligilble customer, float orderTotalPLN){
        int pointsEarned = (int) Math.floor(orderTotalPLN * POINTS_PER_PLN);

        customer.addLoyaltyPoints(pointsEarned);

        System.out.println("Loyalty: added " + pointsEarned + " points to " + customer.getLoyaltyPoints() + " total");
    }
}
//End Week 5, Pattern Mediator 1 Oliwier Majewski
