package SnakeAndLadder;

import java.util.ArrayList;
import java.util.List;

public class Game {
    Board board;
    List<Player> players;
    Dice dice;

    public Game(int size, int snakes, int ladders, int players, int faces) {
        this.board = new Board(size, snakes, ladders);
        this.players = initializePlayers(players);
        this.dice = new Dice(faces);
    }

    private  List<Player> initializePlayers(int players) {
        List<Player> allPlayers = new ArrayList<>();
        for(int i=0; i<players; i++) {
            allPlayers.add(new Player("Player " + (i+1)));
        }
        return allPlayers;
    }

    public void playGame() {
        int currentPlayer = 0;
        Player winner = null;

        while(winner == null) {
            int num = dice.roll();
            Player current = players.get(currentPlayer);

            if(current.position + num > board.size * board.size - 1) {
                currentPlayer = (currentPlayer + 1) % players.size();
                continue;
            }

            current.position += num;

            Jump snake = board.isSnake(current.position);
            if(snake != null) {
                current.position = snake.end;
            }

            Jump ladder = board.isLadder(current.position);
            if(ladder != null) {
                current.position = ladder.end;
            }

            if(isWinner(current)) {
                winner = current;
            }

            currentPlayer = (currentPlayer + 1) % players.size();
        }

        System.out.println("Winner is: " + winner.name);
    }

    private boolean isWinner(Player current) {
        return current.position == board.size * board.size - 1;
    }
}
