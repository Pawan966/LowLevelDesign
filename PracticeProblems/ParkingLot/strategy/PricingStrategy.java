package ParkingLot.strategy;

import ParkingLot.Dto.ticket.Ticket;

public interface PricingStrategy {
    Double calculatePrice(Ticket ticket);
}
