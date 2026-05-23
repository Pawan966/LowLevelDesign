package TicTacToe;

import TicTacToe.service.Game;

public class ClientApplication {
    public static void main(String[] args) {
        Game game = new Game(3);
        TicTacToeApplication application = new TicTacToeApplication(game);
        application.playGame();
    }
}
