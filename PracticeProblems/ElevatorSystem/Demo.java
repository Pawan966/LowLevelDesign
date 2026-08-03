package ElevatorSystem;

import ElevatorSystem.controller.ElevatorController;
import ElevatorSystem.dto.Direction;
import ElevatorSystem.strategy.NearestDispatchStrategy;

public class Demo {
    public  static void main(String[] args) {
        ElevatorController controller = new ElevatorController(3, new NearestDispatchStrategy());
        controller.submitExternalRequest(5, Direction.UP);
        controller.submitExternalRequest(2, Direction.DOWN);
        controller.submitInternalRequest(1, 10);
    }
}
