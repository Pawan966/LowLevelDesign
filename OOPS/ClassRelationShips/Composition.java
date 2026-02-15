package ClassRelationShips;

/*
* Composition is a stronger form of aggregation with full ownership—if the whole is destroyed, the parts cannot exist independently.
  Think of a House and its Rooms: without the house, the rooms cease to exist.
* */
class Room {
    String name;
    Room(String name) {
        this.name = name;
    }
}

class House {
    // Composition: A House is composed of Rooms.
    private Room livingRoom;
    private Room kitchen;

    public House() {
        // Rooms are created and owned by the House.
        livingRoom = new Room("Living Room");
        kitchen = new Room("Kitchen");
    }
    void showHouse() {
        System.out.println(
                "House contains: " + livingRoom.name + " and " + kitchen.name);
    }
}
public class Composition {
    public static void main(String[] args) {
        House house = new House();
        house.showHouse();
    }
}
