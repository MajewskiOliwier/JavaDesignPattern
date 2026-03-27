package allnew.okk.shop.command;

import java.util.ArrayList;
import java.util.List;
import allnew.okk.shop.mediator.ShopEvent;
import allnew.okk.shop.mediator.ShopEventMediator;

// Week 5, Pattern Command 2
// Invoker class that stores the history of executed commands.
// It allows for executing new requests and undoing the previous ones.
public class ShopCommandInvoker {

    private final List<ShopCommand> commandHistory = new ArrayList<>();

    private final ShopEventMediator mediator;
    public ShopCommandInvoker(ShopEventMediator mediator) {
        this.mediator = mediator;
    }

    // Executes the command and saves it to the history stack
    public void executeCommand(ShopCommand command, ShopEvent event, allnew.okk.shop.model.BaseShop shop) {
        command.execute();
        commandHistory.add(command);
        System.out.println("[INVOKER] Command executed and added to history.");

        // Powiadomienie Mediatora o zmianie
        if (mediator != null) {
            mediator.notify(shop, event);
        }
    }

    // Reverts the last executed command
    public boolean undoLastCommand() {
        if (commandHistory.isEmpty()) {
            System.out.println("[INVOKER] No commands to undo.");
            return false;
        }

        // Retrieve and remove the last command from the list
        ShopCommand lastCommand = commandHistory.remove(commandHistory.size() - 1);
        lastCommand.undo();
        System.out.println("[INVOKER] Last command undone successfully.");
        return true;
    }

    public int getHistorySize() {
        return commandHistory.size();
    }
}
// End Week 5, Pattern Command 2