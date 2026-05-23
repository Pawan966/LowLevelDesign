package TicTacToe.service;

import TicTacToe.Dto.Board;
import TicTacToe.Dto.Piece;
import TicTacToe.Dto.Player;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;

    public Game(int size) {
        this.board = new Board(size);
        this.player1 = new Player(Piece.X, "Player 1");
        this.player2 = new Player(Piece.O, "Player 2");
    }

    public void playGame() {
        Player currentPlayer = player1;
        Player winner = null;

        int min = 0, max = board.getSize() * board.getSize();
        while (winner == null) {
            int count = 0;

            while(count < max) {
                int num = (int) (Math.random() * (max - min) + min);
                int row = num / board.getSize();
                int col = num % board.getSize();

                if(board.placePiece(currentPlayer, row, col)) {
                    break;
                }
                count++;
            }

            if(count == max) {
                break;
            }

            if(isWinner(currentPlayer)) {
                winner = currentPlayer;
            }

            currentPlayer = currentPlayer == player1 ? player2 : player1;
        }

        if(winner != null) {
            System.out.println("Winner is: " + winner.getName());
        } else {
            System.out.println("Draw");
        }
    }

    private boolean isWinner(Player player) {
        // check row
        for (int i = 0; i < board.getSize(); i++) {
            boolean isWinner = true;
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getBoard()[i][j] != (player.getPiece() == Piece.X ? 'X' : 'O')) {
                    isWinner = false;
                    break;
                }
            }
            if (isWinner) {
                return true;
            }
        }

        // check col
        for (int j = 0; j < board.getSize(); j++) {
            boolean isWinner = true;
            for (int i = 0; i < board.getSize(); i++) {
                if (board.getBoard()[i][j] != (player.getPiece() == Piece.X ? 'X' : 'O')) {
                    isWinner = false;
                    break;
                }
            }
            if (isWinner) {
                return true;
            }
        }

        // check diagonal
        boolean isWinner = true;
        for (int i = 0; i < board.getSize(); i++) {
            if (board.getBoard()[i][i] != (player.getPiece() == Piece.X ? 'X' : 'O')) {
                isWinner = false;
                break;
            }
        }
        if(isWinner) {
            return true;
        }

        // check other diagonal
        isWinner = true;
        for (int i = 0; i < board.getSize(); i++) {
            if (board.getBoard()[i][board.getSize() - 1 - i] != (player.getPiece() == Piece.X ? 'X' : 'O')) {
                isWinner = false;
                break;
            }
        }
        return isWinner;
    }
}
