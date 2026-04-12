package VendingMachine;

import VendingMachine.Dto.Coin;
import VendingMachine.Dto.Item;
import VendingMachine.Inventory.Inventory;
import VendingMachine.state.IdleState;
import VendingMachine.state.VendingMachineState;

public class VendingMachine {
    private VendingMachineState currentState;

    private final Inventory inventory;
    private int balance;
    private Item selectedItem;

    public VendingMachine() {
        this.inventory = new Inventory();
        this.balance = 0;
        this.currentState = IdleState.getInstance();
    }

    // public API (delegate to current state)
    public void insertCoin(Coin coin){
        currentState.insertCoin(this, coin);
    }

    public void selectItem(Item item) {
        currentState.selectItem(this, item);
    }

    public void dispense() {
        currentState.dispense(this);
    }

    public void cancel() {
        currentState.cancel(this);
    }

    // State Management
    public void setState(VendingMachineState state) {
        this.currentState = state;
    }

    // Balance Management
    public void addBalance(int amount) {
        this.balance += amount;
    }

    public int getBalance() {
        return this.balance;
    }

    public void resetBalance() {
        this.balance = 0;
    }

    // Item helpers
    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }

    public Item getSelectedItem() {
        return this.selectedItem;
    }

    // Inventory
    public Inventory getInventory() {
        return this.inventory;
    }
}
