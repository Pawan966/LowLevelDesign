package VendingMachine.state;

import VendingMachine.Dto.Coin;
import VendingMachine.Dto.Item;
import VendingMachine.VendingMachine;

public class IdleState implements VendingMachineState{
    private static final IdleState INSTANCE = new IdleState();

    public static IdleState getInstance() {
        return INSTANCE;
    }

    private IdleState() {
        // private constructor to prevent instantiation
    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) {
        machine.addBalance(coin.getValue());
        System.out.println("Inserted coin: " + coin.getValue());
        System.out.println("Current balance: " + machine.getBalance());
        machine.setState(HasCoinState.getInstance());
    }

    @Override
    public void cancel(VendingMachine machine) {
        System.out.println("No coins inserted. Nothing to cancel.");
    }

    @Override
    public void dispense(VendingMachine machine) {
        System.out.println("Please select an item first.");
    }

    @Override
    public void selectItem(VendingMachine machine, Item item) {
        System.out.println("Please insert coins first.");
    }
}
