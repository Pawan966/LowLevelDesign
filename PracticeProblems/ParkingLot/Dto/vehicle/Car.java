package ParkingLot.Dto.vehicle;

public class Car extends Vehicle {
    public Car(String licenseNumber) {
        super(licenseNumber, VehicleType.CAR);
    }
}
