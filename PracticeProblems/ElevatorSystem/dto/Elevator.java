package ElevatorSystem.dto;


import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/*
* Represents a single elevator in the system. Runs on it's own thread.
* Only responsible for its own movement and processing its internal stops.
* */
public class Elevator implements Runnable{
    private final int id;

    /*
    * Since your elevator runs on its own thread and will be accessed by multiple threads (controller assigning requests, UI reading status, elevator updating position),
    * atomics ensure all operations are thread-safe, consistent, and performant without explicit locking!
    * */
    private final AtomicInteger currentFloor;
    private final AtomicReference<Direction> currentDirection;

    // sorted sets to store requests in order
    private final ConcurrentSkipListSet<Integer> upRequests;
    private final ConcurrentSkipListSet<Integer> downRequests;

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = new AtomicInteger(0);
        this.currentDirection = new AtomicReference<>(Direction.IDLE);

        // lowest to highest
        this.upRequests = new ConcurrentSkipListSet<>();

        // highest to lowest
        this.downRequests = new ConcurrentSkipListSet<>((a, b) -> Integer.compare(b, a));
    }

    public void addRequest(Request request) {
        if(request.getDirection() == Direction.UP) {
            upRequests.add(request.getRequestedFloor());
        } else {
            downRequests.add(request.getRequestedFloor());
        }

        if(currentDirection.get() == Direction.IDLE) {
            currentDirection.set(request.getRequestedFloor() > currentFloor.get() ? Direction.UP : Direction.DOWN);
        }
    }

    @Override
    public void run() {
        while(true) {
            try{
                processRequests();
                Thread.sleep(1000); // simulate elevator moving
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void processRequests() throws  InterruptedException {
        if(currentDirection.get() == Direction.UP || currentDirection.get() == Direction.IDLE){
            processUpRequests();
            processDownRequests();
        }else{
            processDownRequests();
            processUpRequests();
        }

        if(upRequests.isEmpty() && downRequests.isEmpty()) {
            currentDirection.set(Direction.IDLE);
        }
    }

    private void processUpRequests() throws InterruptedException {
        while(!upRequests.isEmpty()) {
            int nextFloor = upRequests.pollFirst();
            moveToFloor(nextFloor);
        }

        if(!downRequests.isEmpty()) {
            currentDirection.set(Direction.DOWN);
        }
    }

    private void processDownRequests() throws InterruptedException {
        while(!downRequests.isEmpty()) {
            int nextFloor = downRequests.pollFirst();
            moveToFloor(nextFloor);
        }

        if(!upRequests.isEmpty()) {
            currentDirection.set(Direction.UP);
        }
    }

    private void moveToFloor(int floor) throws InterruptedException {
        System.out.println("Elevator " + id + " moving from " + currentFloor.get() + " to " + floor);

        // simulate moving
        Thread.sleep(Math.abs(currentFloor.get() - floor) * 500L);

        // move elevator to the floor
        currentFloor.set(floor);
        System.out.println("Elevator " + id + " arrived at " + floor);
    }

    public int getCurrentFloor() {
        return currentFloor.get();
    }

    public Direction getCurrentDirection() {
        return currentDirection.get();
    }

    public int getId() {
        return id;
    }
}
