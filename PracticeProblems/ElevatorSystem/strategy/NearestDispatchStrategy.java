package ElevatorSystem.strategy;

import ElevatorSystem.dto.Direction;
import ElevatorSystem.dto.Elevator;
import ElevatorSystem.dto.Request;

import java.util.List;

public class NearestDispatchStrategy implements  DispatchStrategy {
    @Override
    public Elevator selectOptimalElevator(List<Elevator> elevators, Request request) {
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            int distance = Math.abs(elevator.getCurrentFloor() - request.getRequestedFloor());
            Direction elevatorDirection = elevator.getCurrentDirection();

            boolean isMovingTowards = (elevatorDirection == Direction.UP && elevator.getCurrentFloor() <= request.getRequestedFloor()) ||
                    (elevatorDirection == Direction.DOWN && elevator.getCurrentFloor() >= request.getRequestedFloor());

            if ((elevatorDirection == Direction.IDLE || isMovingTowards) && distance < minDistance) {
                minDistance = distance;
                bestElevator = elevator;
            }
        }

        return bestElevator != null ? bestElevator : elevators.getFirst();
    }
}
