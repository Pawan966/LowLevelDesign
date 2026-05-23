package SnakeAndLadder.service;

import SnakeAndLadder.Dto.Board;
import SnakeAndLadder.Dto.Dice;
import SnakeAndLadder.Dto.Jump;
import SnakeAndLadder.Dto.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Board board;
    private final List<Player> players;
    private final Dice dice;

    public Game(int size, int snakes, int ladders, int players, int faces) {
        this.board = new Board(size, snakes, ladders);
        this.players = initializePlayers(players);
        this.dice = new Dice(faces);
    }

    private List<Player> initializePlayers(int players) {
        List<Player> allPlayers = new ArrayList<>();
        for (int i = 0; i < players; i++) {
            allPlayers.add(new Player("Player " + (i + 1)));
        }
        return allPlayers;
    }

    public void playGame() {
        int currentPlayerIdx = 0;
        Player winner = null;

        while (winner == null) {
            int num = dice.roll();
            Player current = players.get(currentPlayerIdx);

            if (current.getPosition() + num > board.getSize() * board.getSize() - 1) {
                currentPlayerIdx = (currentPlayerIdx + 1) % players.size();
                continue;
            }

            current.setPosition(current.getPosition() + num);

            Jump snake = board.isSnake(current.getPosition());
            if (snake != null) {
                current.setPosition(snake.getEnd());
            }

            Jump ladder = board.isLadder(current.getPosition());
            if (ladder != null) {
                current.setPosition(ladder.getEnd());
            }

            if (isWinner(current)) {
                winner = current;
            }

            currentPlayerIdx = (currentPlayerIdx + 1) % players.size();
        }

        System.out.println("Winner is: " + winner.getName());
    }

    private boolean isWinner(Player current) {
        return current.getPosition() == board.getSize() * board.getSize() - 1;
    }
}
