package VendingMachine.state;

import VendingMachine.Dto.Coin;
import VendingMachine.Dto.Item;
import VendingMachine.VendingMachine;

public class OutOfStockState implements VendingMachineState {
    private final static OutOfStockState INSTANCE = new OutOfStockState();
    public  static OutOfStockState getInstance() {
        return INSTANCE;
    }
    private OutOfStockState() {}

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) {
        System.out.println("Machine is out of stock. Please try again later.");
    }

    @Override
    public void cancel(VendingMachine machine) {
        System.out.println("Machine is out of stock. Please try again later.");
    }

    @Override
    public void dispense(VendingMachine machine) {
        System.out.println("Machine is out of stock. Please try again later.");
    }

    @Override
    public void selectItem(VendingMachine machine, Item item) {
        System.out.println("Machine is out of stock. Please try again later.");
    }
}
