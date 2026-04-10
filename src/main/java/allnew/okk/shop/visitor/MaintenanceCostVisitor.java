package allnew.okk.shop.visitor;

import allnew.okk.shop.model.OnlineShop;
import allnew.okk.shop.model.PhysicalShop;

// Week 6, Pattern Visitor 2
// Concrete visitor that calculates the total maintenance cost
// of all shops in the network based on their specific type.
public class MaintenanceCostVisitor implements ShopVisitor {
    private double totalCost = 0.0;

    @Override
    public void visit(PhysicalShop physicalShop) {
        // Physical shops have base rent and electricity costs
        double cost = 5000.0;

        // Drop-off points require extra staff/space
        if (physicalShop.isDropOffAvailable()) {
            cost += 1500.0;
        }

        System.out.println("Cost for Physical Shop [" + physicalShop.getName() + "]: " + cost + " PLN");
        totalCost += cost;
    }

    @Override
    public void visit(OnlineShop onlineShop) {
        // Online shops only pay for server hosting and domain maintenance
        double cost = 800.0;

        System.out.println("Cost for Online Shop [" + onlineShop.getName() + "]: " + cost + " PLN");
        totalCost += cost;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
// End Week 6, Pattern Visitor 2