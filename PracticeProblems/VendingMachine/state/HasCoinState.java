package VendingMachine.state;

import VendingMachine.Dto.Coin;
import VendingMachine.Dto.Item;
import VendingMachine.VendingMachine;

public class HasCoinState implements  VendingMachineState {
    private static final HasCoinState INSTANCE = new HasCoinState();
    private  HasCoinState() {}
    public static HasCoinState getInstance() {
        return INSTANCE;
    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) {
        machine.addBalance(coin.getValue());
        System.out.println("Inserted coin: " + coin.getValue());
        System.out.println("Current balance: " + machine.getBalance());
    }

    @Override
    public void cancel(VendingMachine machine) {
        System.out.println("Cancelling transaction");
        int refund = machine.getBalance();
        machine.resetBalance();
        System.out.println("Refunded: " + refund);
        machine.setState(IdleState.getInstance());
    }

    @Override
    public void dispense(VendingMachine machine) {
        System.out.println("Please select an item first.");
    }

    @Override
    public void selectItem(VendingMachine machine, Item item) {
        if(!machine.getInventory().isItemAvailable(item)) {
            System.out.println("Item not available");
            return;
        }

        if(machine.getBalance() < item.getPrice()) {
            System.out.println("Insufficient balance, please add " + (item.getPrice()- machine.getBalance()) + " more");
            return;
        }

        machine.setSelectedItem(item);
        System.out.println("Selected item: " + item.getName());
        machine.setState(ItemSelectedState.getInstance());
    }
}
