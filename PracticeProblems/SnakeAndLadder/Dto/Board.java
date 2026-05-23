package SnakeAndLadder.Dto;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Jump> snakes;
    private final List<Jump> ladders;
    private final int size;

    public Board(int size, int snakes, int ladders) {
        this.size = size;
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();

        initializeBoard(snakes, ladders);
    }

    private void initializeBoard(int snakes, int ladders) {
        for (int i = 0; i < snakes; i++) {
            int start = generateRandomNumber();
            int end = generateRandomNumber();
            if (start <= end || start == 0 || end == 0 || start == this.size * this.size - 1 || checkIfLadderExists(start, end)) {
                i--;
                continue;
            }
            this.snakes.add(new Jump(start, end));
        }

        for (int i = 0; i < ladders; i++) {
            int start = generateRandomNumber();
            int end = generateRandomNumber();
            if (start >= end || start == 0 || start == this.size * this.size - 1 || checkIfSnakeExists(start, end)) {
                i--;
                continue;
            }
            this.ladders.add(new Jump(start, end));
        }
    }

    private int generateRandomNumber() {
        int min = 0, max = this.size * this.size;
        return (int) (Math.random() * (max - min) + min);
    }

    private boolean checkIfLadderExists(int start, int end) {
        for (Jump ladder : ladders) {
            if (ladder.getStart() == start || ladder.getStart() == end || ladder.getEnd() == start) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIfSnakeExists(int start, int end) {
        for (Jump snake : snakes) {
            if (snake.getStart() == start || snake.getStart() == end || snake.getEnd() == start) {
                return true;
            }
        }
        return false;
    }

    public Jump isSnake(int position) {
        for (Jump snake : snakes) {
            if (snake.getStart() == position) {
                return snake;
            }
        }
        return null;
    }

    public Jump isLadder(int position) {
        for (Jump ladder : ladders) {
            if (ladder.getStart() == position) {
                return ladder;
            }
        }
        return null;
    }

    public int getSize() {
        return size;
    }
}
