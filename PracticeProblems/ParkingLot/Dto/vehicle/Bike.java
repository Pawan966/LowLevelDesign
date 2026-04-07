package ParkingLot.Dto.vehicle;

public class Bike extends Vehicle {
    public Bike(String licenseNumber) {
        super(licenseNumber, VehicleType.BIKE);
    }
}
