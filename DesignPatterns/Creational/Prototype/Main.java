package Creational.Prototype;

// Prototype pattern let's you copy existing objects without making your code dependent on their classes.
// It delegates the cloning process to the object itself.

/*
* Where to use:
* 1. Sometimes cloning is a tedious task
* 2. Creating an copy is an expensive task, eg requires an network call to populate the certain fields
* 3. Cloning directly can make your code dependent on the other classes.
* */
public class Main {
    public static void main(String[] args) {
        Vehicle car = new Vehicle("Tesla", "Model 3", new Engine(200, 4));

        Vehicle car2 = new Vehicle(car);
        car2.setModel("Model S");

        System.out.println(car.getModel());
        System.out.println(car2.getModel());
    }
}
