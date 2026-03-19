package allnew.okk.basket.composite;

import allnew.okk.account.Adapter.AccountDisplayable;
import allnew.okk.basket.Command.BasketCommand;
import allnew.okk.basket.Interpreter.BasketInterpreterExpression;
import allnew.okk.basket.Iterator.BasketIterator;
import allnew.okk.basket.Visitor.BasketVisitor;

import java.util.*;
import java.util.stream.Collectors;

//Week 3, Pattern Composite Oliwier Majewski
public class ShoppingBasket implements PurchasableItem, Iterable<PurchasableItem>{
    List<PurchasableItem> items = new ArrayList<>();
    private final List<BasketCommand> commandHistory = new ArrayList<>();

    @Override
    public double getPrice() {
        double totalPrice = 0;

        for(PurchasableItem item : items){
            totalPrice += item.getPrice();
        }

        return totalPrice;
    }

    //Week 5, Pattern Iterator Oliwier Majewski
    @Override
    public Iterator<PurchasableItem> iterator() {
        return new BasketIterator(items);
    }
    //End Week 5, Pattern Iterator Oliwier Majewski

    @Override
    public String getItemName() {
        return "";
    }

    @Override
    public String getSellerName() {
        return "";
    }

    @Override
    public String getSellerID() {
        return "";
    }

    @Override
    public AccountDisplayable getSellerAccount() {
        return null;
    }

    @Override
    public List<PurchasableItem> getChildren() {
        return items;
    }


    //Week 6, Pattern Visitor Oliwier Majewski
    @Override
    public void accept(BasketVisitor visitor) {
        visitor.visit(this);
        for(PurchasableItem item : items){
            item.accept(visitor);
        }
    }
    //End Week 6, Pattern Visitor Oliwier Majewski

    public void OrganizeBySeller(){
        Map<String, List<PurchasableItem>> groupedBySeller = items.stream()
                .filter(item -> !(item instanceof SellerBasket))
                .collect(Collectors.groupingBy(PurchasableItem::getSellerID));

        items.clear();

        for (Map.Entry<String, List<PurchasableItem>> entry : groupedBySeller.entrySet()){
            String sellerID = entry.getKey();
            List<PurchasableItem> sellerProducts = entry.getValue();

            String sellerName = sellerProducts.getFirst().getSellerName();
            AccountDisplayable sellerAccount = sellerProducts.getFirst().getSellerAccount();

            SellerBasket group = new SellerBasket(sellerID,sellerName,sellerAccount);
            sellerProducts.forEach(group::addItem);
            items.add(group);
        }
    }

    public void addItem(PurchasableItem item) {
        items.add(item);  // Products moved into this list
    }

    //Week 5, Pattern Command 1 Oliwier Majewski
    public void executeCommand(BasketCommand command) {
        command.Execute();
        commandHistory.addLast(command);
    }

    public boolean undo() {
        if (commandHistory.isEmpty()) {
            System.out.println("Nothing to undo");
            return false;
        }
        commandHistory.removeLast().undo();
        return true;
    }

    public int historySize() {
        return commandHistory.size();
    }

    public void removeItem(PurchasableItem item) {
        items.remove(item);
    }
    //End Week 5, Pattern Command 1 Oliwier Majewski

    //Week 5, Pattern Interpreter 1 Oliwier Majewski
    public List<PurchasableItem> filter(BasketInterpreterExpression expression) {
        return items.stream().filter(expression::evaluate).toList();
    }
    //End Week 5, Pattern Interpreter 1 Oliwier Majewski
}
//End Week 3, Pattern Composite Oliwier Majewski