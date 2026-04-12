package VendingMachine;

import VendingMachine.Dto.Coin;
import VendingMachine.Dto.Item;

public class VendingMachineDemo {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();

        Item coke = new Item("Coke", 50);
        Item chips = new Item("Chips", 25);
        Item water = new Item("Water", 20);

        vendingMachine.getInventory().addItem(coke, 5);
        vendingMachine.getInventory().addItem(chips, 3);
        vendingMachine.getInventory().addItem(water, 2);

        vendingMachine.getInventory().display();

        // Scenario 1: Normal purchase with change
        System.out.println(" ---- Scenario 1: Buy chips and pay Rs30---- ");
        vendingMachine.insertCoin(Coin.TEN);
        vendingMachine.insertCoin(Coin.TEN);
        vendingMachine.insertCoin(Coin.TEN);
        vendingMachine.selectItem(chips);
        vendingMachine.dispense();


        // Scenario 2: Insufficient balance
        System.out.println(" ---- Scenario 2: Buy chips and pay Rs25---- ");
        vendingMachine.insertCoin(Coin.TEN);
        vendingMachine.insertCoin(Coin.TEN);
        vendingMachine.insertCoin(Coin.FIVE);
        vendingMachine.selectItem(coke);
        vendingMachine.cancel();
    }
}
