package ParkingLot.Dto.parking;

import ParkingLot.Dto.vehicle.Vehicle;
import ParkingLot.Dto.vehicle.VehicleType;

public class ParkingSpot {
    private final String spotId;
    private final VehicleType vehicleType;
    private Vehicle vehicle;

    public  ParkingSpot(String spotId, VehicleType vehicleType) {
        this.spotId = spotId;
        this.vehicleType = vehicleType;
    }

    public boolean isSpotAvailable() {
        return vehicle == null;
    }

    public String getSpotId() {
        return spotId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public synchronized boolean parkVehicle(Vehicle vehicle) {
        if(!isSpotAvailable()) {
            return false;
        }

        if(vehicle.getVehicleType() != vehicleType) {
            return false;
        }

        this.vehicle = vehicle;
        return true;
    }

    public synchronized void removeVehicle() {
        this.vehicle = null;
    }
}
