package Creational.Prototype;

public class Vehicle {
    private String brand;
    private String model;
    private Engine engine;

    public Vehicle(String brand, String model, Engine engine) {
        this.brand = brand;
        this.model = model;
        this.engine = engine;
    }

    // copy constructor
    public Vehicle(Vehicle other) {
        this.brand = other.brand;
        this.model = other.model;
        this.engine = new Engine(other.engine);
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
