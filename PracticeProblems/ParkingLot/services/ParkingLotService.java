package ParkingLot.services;

import ParkingLot.Dto.parking.ParkingFloor;
import ParkingLot.Dto.parking.ParkingSpot;
import ParkingLot.Dto.ticket.Ticket;
import ParkingLot.Dto.vehicle.Vehicle;
import ParkingLot.Dto.vehicle.VehicleType;
import ParkingLot.exception.ParkingException;
import ParkingLot.strategy.PricingStrategy;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLotService {
    private final Map<String, Ticket> activeTickets;
    private final List<ParkingFloor> parkingFloors;
    private PricingStrategy pricingStrategy;

    public ParkingLotService(List<ParkingFloor> parkingFloors, PricingStrategy pricingStrategy) {
        this.parkingFloors = parkingFloors;
        this.pricingStrategy = pricingStrategy;
        this.activeTickets = new ConcurrentHashMap<>();
    }

    /*
    * Using concurrent hashmap because:
    * 1. Let's say a bike already exist in the system with ticket hash 100
    * 2. A car came and ticket hash generated for it is also 100
    * 3. Now bike is leaving at the same time the car came in.
    * 4. Internally we are trying to remove and put on the list in the hashmap with same hash which will break
    *    in case of normal hashmap.
    * */

    public Ticket parkVehicle(Vehicle vehicle) {
        ParkingSpot parkingSpot = findAvailableParkingSpot(vehicle.getVehicleType());

        if(!parkingSpot.parkVehicle(vehicle)) {
            throw new ParkingException("Parking failed for vehicle: " + vehicle.getLicenseNumber());
        }

        Ticket ticket = new Ticket(parkingSpot);

        Ticket existing = activeTickets.putIfAbsent(ticket.getTicketId(), ticket);
        if(existing != null) {
            // retry
            return parkVehicle(vehicle);
        }

        return ticket;
    }

    public double unparkVehicle(Ticket ticket) {
        if(!activeTickets.containsKey(ticket.getTicketId())) {
            throw new ParkingException("Invalid ticket: " + ticket.getTicketId());
        }

        ticket.setExitTime();
        ticket.closeTicket(pricingStrategy.calculatePrice(ticket));
        ticket.getParkingSpot().removeVehicle();
        activeTickets.remove(ticket.getTicketId());
        return ticket.getCharges();
    }

    private ParkingSpot findAvailableParkingSpot(VehicleType vehicleType) {
        for (ParkingFloor floor : parkingFloors) {
            for (ParkingSpot spot : floor.getParkingSpots()) {
                if (spot.getVehicleType() == vehicleType && spot.isSpotAvailable()) {
                    return spot;
                }
            }
        }
        throw new ParkingException("No available parking spot for " + vehicleType);
    }
}
