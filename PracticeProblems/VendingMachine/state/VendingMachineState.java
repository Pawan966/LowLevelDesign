package VendingMachine.state;

import VendingMachine.Dto.Coin;
import VendingMachine.Dto.Item;
import VendingMachine.VendingMachine;

public interface VendingMachineState {
    void insertCoin(VendingMachine machine, Coin coin);
    void selectItem(VendingMachine machine, Item item);
    void dispense(VendingMachine machine);
    void cancel(VendingMachine machine);
}
