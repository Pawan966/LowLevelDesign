package SnakeAndLadder.Dto;

public class Dice {
    private final int faces;
    public Dice(int faces) {
        this.faces = faces;
    }

    public int roll() {
        int min = 1;
        return (int) (Math.random() * (faces - min + 1) + min);
    }

    public int getFaces() {
        return faces;
    }
}
