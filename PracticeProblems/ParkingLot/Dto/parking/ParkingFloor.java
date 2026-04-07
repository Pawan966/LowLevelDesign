package ParkingLot.Dto.parking;

import java.util.List;

public class ParkingFloor {
    private final String floorId;
    private final List<ParkingSpot> parkingSpots;

    public ParkingFloor(String floorId, List<ParkingSpot> parkingSpots) {
        this.floorId = floorId;
        this.parkingSpots = parkingSpots;
    }

    public List<ParkingSpot> getParkingSpots() {
        /*
        * The reference cannot be reassigned
          You cannot do: parkingSpots = new ArrayList<>()
          but you can add/remove elements from the list
          that's why returning a copy everytime so that client code cannot change original list
        * */
        return List.copyOf(parkingSpots);
    }

    public String getFloorId() {
        return floorId;
    }
}
