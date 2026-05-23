package SnakeAndLadder;

import SnakeAndLadder.service.Game;

public class ClientApplication {
    public static void main(String[] args) {
        Game game = new Game(10, 5, 5, 3, 6);

        SnakeAndLadderApplication application = new SnakeAndLadderApplication(game);
        application.playGame();
    }
}
