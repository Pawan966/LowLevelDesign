package VendingMachine.state;

import VendingMachine.Dto.Coin;
import VendingMachine.Dto.Item;
import VendingMachine.VendingMachine;

public class ItemSelectedState implements VendingMachineState {
    private static final ItemSelectedState INSTANCE = new ItemSelectedState();
    private  ItemSelectedState() {}
    public static ItemSelectedState getInstance() {
        return INSTANCE;
    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) {
        System.out.println("Item already selected. Dispensing shortly...");
    }

    @Override
    public void cancel(VendingMachine machine) {
        int refund = machine.getBalance();
        machine.resetBalance();
        machine.setSelectedItem(null);
        System.out.println("Refunded: " + refund);
        machine.setState(IdleState.getInstance());
    }

    @Override
    public void dispense(VendingMachine machine) {
        Item item = machine.getSelectedItem();
        machine.getInventory().deductItem(item);

        int change = machine.getBalance() - item.getPrice();
        machine.resetBalance();
        machine.setSelectedItem(null);
        machine.setState(IdleState.getInstance());
        System.out.println("Dispensing " + item.getName() + " and change: " + change);
    }

    @Override
    public void selectItem(VendingMachine machine, Item item) {
        System.out.println("Item already selected. Dispensing shortly...");
    }
}
