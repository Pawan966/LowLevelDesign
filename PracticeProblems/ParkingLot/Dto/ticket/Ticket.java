package ParkingLot.Dto.ticket;

import ParkingLot.Dto.parking.ParkingSpot;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private final String ticketId;
    private final ParkingSpot parkingSpot;
    private final LocalDateTime entryTime;

    private LocalDateTime exitTime;
    private Double charges;

    public Ticket(ParkingSpot parkingSpot) {
        this.ticketId = UUID.randomUUID().toString();
        this.parkingSpot = parkingSpot;
        this.entryTime = LocalDateTime.now();
    }

    public void closeTicket(Double charges) {
        this.charges = charges;
    }

    public void setExitTime() {
        this.exitTime = LocalDateTime.now();
    }

    // getters
    public String getTicketId() {
        return ticketId;
    }
    public Double getCharges() {
        return charges;
    }
    public  LocalDateTime getEntryTime() {
        return entryTime;
    }
    public LocalDateTime getExitTime() {
        return exitTime;
    }
    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

}
