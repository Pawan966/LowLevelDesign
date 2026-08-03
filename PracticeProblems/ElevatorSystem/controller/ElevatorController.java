package ElevatorSystem.controller;

import ElevatorSystem.dto.Direction;
import ElevatorSystem.dto.Elevator;
import ElevatorSystem.dto.Request;
import ElevatorSystem.strategy.DispatchStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ElevatorController {
    private final List<Elevator> elevators;
    private DispatchStrategy dispatchStrategy;

    public ElevatorController(int numElevators, DispatchStrategy dispatchStrategy) {
        this.elevators = new ArrayList<>();
        for (int i = 0; i < numElevators; i++) {
            Elevator elevator = new Elevator(i);
            elevators.add(elevator);
            new Thread(elevator).start();
        }
        this.dispatchStrategy = dispatchStrategy;
    }

    public void setDispatchStrategy(DispatchStrategy dispatchStrategy) {
        this.dispatchStrategy = dispatchStrategy;
    }

    public void submitExternalRequest(int floor, Direction direction) {
        Request request = new Request(floor, direction);
        Elevator optimalElevator = dispatchStrategy.selectOptimalElevator(elevators, request);
        optimalElevator.addRequest(request);
    }

    public void submitInternalRequest(int elevatorId, int targetFloor) {
        Optional<Elevator> optionalElevator = elevators.stream().filter(e -> e.getId() == elevatorId).findFirst();
        if(optionalElevator.isPresent()) {
            Elevator elevator = optionalElevator.get();
            Direction direction = elevator.getCurrentFloor() < targetFloor ? Direction.UP : Direction.DOWN;
            elevator.addRequest(new Request(targetFloor, direction));
        }else{
            System.out.println("Invalid elevator id: " + elevatorId);
        }
    }
}
