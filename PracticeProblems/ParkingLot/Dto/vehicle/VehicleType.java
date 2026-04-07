package ParkingLot.Dto.vehicle;

public enum VehicleType {
    BIKE(20), CAR(30), TRUCK(50);

    private final double pricePerHour;

    // enum constructors are always private
    private VehicleType(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }
}
