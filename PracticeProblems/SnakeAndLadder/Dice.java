package SnakeAndLadder;

public class Dice {
    int faces;
    public Dice(int faces) {
        this.faces = faces;
    }

    public int roll() {
        int min = 1, max = faces;
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
