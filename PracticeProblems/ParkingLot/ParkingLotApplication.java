package ParkingLot;

import ParkingLot.Dto.ticket.Ticket;
import ParkingLot.Dto.vehicle.Vehicle;
import ParkingLot.services.ParkingLotService;

public class ParkingLotApplication {
    private final ParkingLotService parkingLotService;

    public ParkingLotApplication(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        return parkingLotService.parkVehicle(vehicle);
    }

    public double unparkVehicle(Ticket ticket) {
        return parkingLotService.unparkVehicle(ticket);
    }
}
