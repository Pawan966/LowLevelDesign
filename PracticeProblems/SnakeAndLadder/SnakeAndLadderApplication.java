package SnakeAndLadder;

import SnakeAndLadder.service.Game;

public class SnakeAndLadderApplication {
    private final Game game;

    public SnakeAndLadderApplication(Game game) {
        this.game = game;
    }

    public void playGame() {
        game.playGame();
    }
}
