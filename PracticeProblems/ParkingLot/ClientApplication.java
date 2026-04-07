package ParkingLot;

import ParkingLot.Dto.parking.ParkingFloor;
import ParkingLot.Dto.parking.ParkingSpot;
import ParkingLot.Dto.ticket.Ticket;
import ParkingLot.Dto.vehicle.Bike;
import ParkingLot.Dto.vehicle.Car;
import ParkingLot.Dto.vehicle.Vehicle;
import ParkingLot.Dto.vehicle.VehicleType;
import ParkingLot.services.ParkingLotService;
import ParkingLot.strategy.HourlyPricingStrategy;
import ParkingLot.strategy.PricingStrategy;

import java.util.List;

public class ClientApplication {
    public static void main(String[] args) {
        ParkingSpot spot1 = new ParkingSpot("1", VehicleType.BIKE);
        ParkingSpot spot2 = new ParkingSpot("2", VehicleType.CAR);

        ParkingFloor groundFloor = new ParkingFloor("1", List.of(spot1));
        ParkingFloor firstFloor = new ParkingFloor("2", List.of(spot2));

        PricingStrategy pricingStrategy = new HourlyPricingStrategy();

        ParkingLotService parkingLotService = new ParkingLotService(List.of(groundFloor, firstFloor), pricingStrategy);
        ParkingLotApplication parkingLotApplication = new ParkingLotApplication(parkingLotService);


        Vehicle bike = new Bike("123");
        Vehicle car = new Car("456");

        // park bike
        Ticket ticket = parkingLotApplication.parkVehicle(bike);

        // park car
        Ticket ticket2 = parkingLotApplication.parkVehicle(car);
    }
}
