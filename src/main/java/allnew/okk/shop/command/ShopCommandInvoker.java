package allnew.okk.shop.command;

import java.util.ArrayList;
import java.util.List;
import allnew.okk.shop.mediator.ShopEvent;
import allnew.okk.shop.mediator.ShopEventMediator;
import allnew.okk.shop.model.BaseShop;

// Week 5, Pattern Command 2
// Invoker class that stores the history of executed commands.
// It allows for executing new requests and undoing the previous ones.
public class ShopCommandInvoker {

    private static final String LOG_EXECUTE = "[INVOKER] Command executed and added to history.";
    private static final String LOG_UNDO = "[INVOKER] Last command undone successfully.";
    private static final int EMPTY_SIZE = 0;
    private static final int INDEX_OFFSET = 1;

    private final List<ShopCommand> commandHistory = new ArrayList<>();
    private final ShopEventMediator mediator;

    public ShopCommandInvoker(ShopEventMediator mediator) {
        this.mediator = mediator;
    }

    public void executeCommand(ShopCommand command, ShopEvent event, BaseShop shop) {
        performExecution(command);
        notifyMediatorIfPresent(shop, event);
    }

    private void performExecution(ShopCommand command) {
        command.execute();
        commandHistory.add(command);
        System.out.println(LOG_EXECUTE);
    }

    private void notifyMediatorIfPresent(BaseShop shop, ShopEvent event) {
        if (mediator != null) {
            mediator.notify(shop, event);
        }
    }

    public void undoLastCommand() {
        if (commandHistory.size() == EMPTY_SIZE) {
            throw new IllegalStateException("Nie ma żadnych komend do cofnięcia.");
        }

        ShopCommand lastCommand = removeLastCommand();
        lastCommand.undo();
        System.out.println(LOG_UNDO);
    }

    private ShopCommand removeLastCommand() {
        int lastIndex = commandHistory.size() - INDEX_OFFSET;
        return commandHistory.remove(lastIndex);
    }

    public int getHistorySize() {
        return commandHistory.size();
    }
}
// End Week 5, Pattern Command 2