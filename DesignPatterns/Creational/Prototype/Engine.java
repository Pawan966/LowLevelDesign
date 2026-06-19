package Creational.Prototype;

public class Engine {
    private int horsePower;
    private int numberOfCylinders;

    public Engine(int horsePower, int numberOfCylinders) {
        this.horsePower = horsePower;
        this.numberOfCylinders = numberOfCylinders;
    }

    // Copy constructor
    public Engine(Engine other) {
        this.horsePower = other.horsePower;
        this.numberOfCylinders = other.numberOfCylinders;
    }
}
