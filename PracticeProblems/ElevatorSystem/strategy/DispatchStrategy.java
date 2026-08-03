package ElevatorSystem.strategy;

import ElevatorSystem.dto.Elevator;
import ElevatorSystem.dto.Request;

import java.util.List;

public interface DispatchStrategy {
    Elevator selectOptimalElevator(List<Elevator> elevators, Request request);
}
