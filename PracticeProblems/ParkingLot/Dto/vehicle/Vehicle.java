package ParkingLot.Dto.vehicle;

public abstract class Vehicle {
    private final String licenseNumber;
    private final VehicleType vehicleType;

    protected Vehicle(String licenseNumber, VehicleType vehicleType) {
        this.licenseNumber = licenseNumber;
        this.vehicleType = vehicleType;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
