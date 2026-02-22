package Polymorphism;

class Vehicle {
    void start() {
        System.out.println("Starting a generic vehicle");
    }

    // static methods are not overridden, they are hidden
    static void speed() {
        System.out.println("Speeding a generic vehicle");
    }

    final void stop() {
        System.out.println("Stopping a generic vehicle");
    }
}

// Subclasses overriding the start method
class Car extends Vehicle {
    @Override
    void start() {
        System.out.println("Starting a car");
    }

    static void speed() {
        System.out.println("Speeding a car");
    }

      // this is wrong as final methods cannot be overridden
//    void stop() {
//        System.out.println("Stopping a car");
//    }
}

class Bike extends Vehicle {
    @Override
    void start() {
        System.out.println("Starting a bike");
    }
}

class Truck extends Vehicle {
    @Override
    void start() {
        System.out.println("Starting a truck");
    }
}

public class DynamicPolymorphism {
    public static void main(String[] args) {
        Vehicle myVehicle;
        // Assign a Car object to the Vehicle reference
        myVehicle = new Car();
        myVehicle.start();// Output: Starting a car
        myVehicle.speed();
        myVehicle.stop();

        // Assign a Bike object to the Vehicle reference
        myVehicle = new Bike();
        myVehicle.start(); // Output: Starting a bike

        // Assign a Truck object to the Vehicle reference
        myVehicle = new Truck();
        myVehicle.start(); // Output: Starting a truck
    }
}
