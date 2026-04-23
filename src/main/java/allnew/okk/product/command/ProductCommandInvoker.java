package allnew.okk.product.command;

import allnew.okk.product.mediator.ProductEvent;
import allnew.okk.product.mediator.ProductEventMediator;
import allnew.okk.product.model.BaseProduct;

import java.util.ArrayList;
import java.util.List;


// week 5, pattern command 2 Jakub Marciniuk
public class ProductCommandInvoker {
    private final List<ProductCommand> commandHistory = new ArrayList<>();
    private final ProductEventMediator mediator;
    private final BaseProduct product;

    public ProductCommandInvoker(ProductEventMediator mediator, BaseProduct product) {
        this.mediator = mediator;
        this.product = product;
    }

    public void executeCommand(ProductCommand command, ProductEvent event) {
        command.execute();
        commandHistory.add(command);
        // week 5, pattern mediator 3, jakub marciniuk
        if(mediator != null) {
            mediator.notify(product, event);
        }
        // end of week 5, pattern mediator 3
    }

    public boolean undoLastCommand() {
        if (commandHistory.isEmpty()) {
            System.out.println("No commands to undo.");
            return false;
        }
        ProductCommand lastCommand = commandHistory.remove(commandHistory.size() - 1);
        lastCommand.undo();
        return true;
    }

    public int historySize() {
        return commandHistory.size();
    }
}
// end of week 5, pattern command 2
