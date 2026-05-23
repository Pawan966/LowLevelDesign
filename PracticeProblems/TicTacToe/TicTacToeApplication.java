package TicTacToe;

import TicTacToe.service.Game;

public class TicTacToeApplication {
    private final Game game;

    public TicTacToeApplication(Game game) {
        this.game = game;
    }

    public void playGame() {
        game.playGame();
    }
}
