package TicTacToe;

public class Game {
    Board board;
    Player player1;
    Player player2;

    public Game(int size) {
        this.board = new Board(size);
        this.player1 = new Player(Piece.X, "Player 1");
        this.player2 = new Player(Piece.O, "Player 2");
    }

    public void playGame() {
        Player currentPlayer = player1;
        Player winner = null;

        int min = 0, max = board.size * board.size;
        while (winner == null) {
            int count = 0;

            while(count < max) {
                int num = (int) (Math.random() * (max - min) + min);
                int row = num / board.size;
                int col = num % board.size;

                if(placePiece(currentPlayer, row, col)) {
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
            System.out.println("Winner is: " + winner.name);
        } else {
            System.out.println("Draw");
        }
    }

    private boolean placePiece(Player player, int row, int col) {
        if (board.board[row][col] == '.') {
            board.board[row][col] = player.piece == Piece.X ? 'X' : 'O';
            return true;
        }
        return false;
    }

    private boolean isWinner(Player player) {
        // check row
        for (int i = 0; i < board.size; i++) {
            boolean isWinner = true;
            for (int j = 0; j < board.size; j++) {
                if (board.board[i][j] != (player.piece == Piece.X ? 'X' : 'O')) {
                    isWinner = false;
                    break;
                }
            }
            if (isWinner) {
                return true;
            }
        }

        // check col
        for (int j = 0; j < board.size; j++) {
            boolean isWinner = true;
            for (int i = 0; i < board.size; i++) {
                if (board.board[i][j] != (player.piece == Piece.X ? 'X' : 'O')) {
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
        for (int i = 0; i < board.size; i++) {
            if (board.board[i][i] != (player.piece == Piece.X ? 'X' : 'O')) {
                isWinner = false;
                break;
            }
        }
        if(isWinner) {
            return true;
        }

        // check other diagonal
        isWinner = true;
        for (int i = 0; i < board.size; i++) {
            if (board.board[i][board.size - 1 - i] != (player.piece == Piece.X ? 'X' : 'O')) {
                isWinner = false;
                break;
            }
        }
        return isWinner;
    }
}
