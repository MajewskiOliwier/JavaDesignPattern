package allnew.okk.shop.command;

import allnew.okk.shop.model.BaseShop;

// Week 7 - Single Responsibility Principle (SRP)
// Week 5, Pattern Command 3
// Concrete command responsible for updating a shop's description.
// It captures the previous state to allow a seamless undo operation.
public class UpdateShopDescriptionCommand implements ShopCommand {

    private static final String LOG_UPDATED = "[COMMAND] Shop description updated to: ";
    private static final String LOG_REVERTED = "[COMMAND] Shop description reverted to: ";

    private final BaseShop shop;
    private final String newDescription;
    private String oldDescription;

    public UpdateShopDescriptionCommand(BaseShop shop, String newDescription) {
        validateInputs(shop, newDescription);
        this.shop = shop;
        this.newDescription = newDescription;
    }

    private void validateInputs(BaseShop shop, String newDescription) {
        if (shop == null) {
            throw new IllegalArgumentException("Obiekt sklepu nie może być pusty.");
        }
        if (newDescription == null) {
            throw new IllegalArgumentException("Nowy opis nie może być pusty.");
        }
    }

    @Override
    public void execute() {
        saveCurrentState();
        applyNewState();
    }

    private void saveCurrentState() {
        this.oldDescription = shop.getDescription();
    }

    private void applyNewState() {
        shop.setDescription(newDescription);
        System.out.println(LOG_UPDATED + newDescription);
    }

    @Override
    public void undo() {
        if (oldDescription == null) {
            throw new IllegalStateException("Nie można cofnąć komendy, która nie została wcześniej wykonana.");
        }
        shop.setDescription(oldDescription);
        System.out.println(LOG_REVERTED + oldDescription);
    }
}
// End Week 5, Pattern Command 3