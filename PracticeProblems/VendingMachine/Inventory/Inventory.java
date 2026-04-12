package VendingMachine.Inventory;

import VendingMachine.Dto.Item;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory {
    private final Map<Item, Integer> stock;

    public Inventory() {
        this.stock = new ConcurrentHashMap<>();
    }

    public void addItem(Item item, int quantity) {
        stock.merge(item, quantity, Integer::sum);
    }

    public boolean isItemAvailable(Item item) {
        return stock.getOrDefault(item, 0) > 0;
    }

    public void deductItem(Item item) {
        stock.compute(item, (key, oldValue) -> {
            if (oldValue == null || oldValue <= 0) {
                throw new RuntimeException("Item not available: " + item.getName());
            }
            return oldValue - 1;
        });
    }

    public void display() {
        System.out.println(" ---- Available items ---- ");
        stock.forEach((item, quantity)->{
            System.out.println(item.getName() + " : " + quantity);
        });
        System.out.println(" ------------------------- ");
    }
}
