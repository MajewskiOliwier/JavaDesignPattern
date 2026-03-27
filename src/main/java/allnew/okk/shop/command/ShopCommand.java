package allnew.okk.shop.command;

// Week 5, Pattern Command 1
// Common interface for all commands executed on shops.
// It mandates the implementation of both execution and undo logic.
public interface ShopCommand {
    void execute();
    void undo();
}
// End Week 5, Pattern Command 1