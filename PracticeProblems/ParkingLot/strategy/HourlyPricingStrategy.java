package ParkingLot.strategy;

import ParkingLot.Dto.ticket.Ticket;

import java.time.Duration;

public class HourlyPricingStrategy implements PricingStrategy {
    @Override
    public Double calculatePrice(Ticket ticket) {
       long hours = Duration.between(
               ticket.getEntryTime(),
               ticket.getExitTime()
       ).toHours();

       return Math.max(1, hours) * ticket.getParkingSpot().getVehicleType().getPricePerHour();
    }
}
