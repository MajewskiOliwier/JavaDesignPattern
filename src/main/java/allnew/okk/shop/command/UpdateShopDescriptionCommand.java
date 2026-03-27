package allnew.okk.shop.command;

import allnew.okk.shop.model.BaseShop;

// Week 5, Pattern Command 3
// Concrete command responsible for updating a shop's description.
// It captures the previous state to allow a seamless undo operation.
public class UpdateShopDescriptionCommand implements ShopCommand {

    private final BaseShop shop;
    private final String newDescription;
    private String oldDescription; // Stores the state before execution

    public UpdateShopDescriptionCommand(BaseShop shop, String newDescription) {
        this.shop = shop;
        this.newDescription = newDescription;
    }

    @Override
    public void execute() {
        // Save the current description before changing it
        this.oldDescription = shop.getDescription();
        // Apply the new description
        shop.setDescription(newDescription);
        System.out.println("[COMMAND] Shop description updated to: " + newDescription);
    }

    @Override
    public void undo() {
        if (oldDescription != null) {
            // Revert to the saved description
            shop.setDescription(oldDescription);
            System.out.println("[COMMAND] Shop description reverted to: " + oldDescription);
        }
    }
}
// End Week 5, Pattern Command 3