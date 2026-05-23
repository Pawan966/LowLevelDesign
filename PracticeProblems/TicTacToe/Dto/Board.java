package TicTacToe.Dto;

public class Board {
    private final int size;
    private final char[][] board;

    public Board(int size) {
        this.size = size;
        this.board = new char[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '.';
            }
        }
    }

    public boolean placePiece(Player player, int row, int col) {
        if (board[row][col] == '.') {
            board[row][col] = player.getPiece() == Piece.X ? 'X' : 'O';
            return true;
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public char[][] getBoard() {
        return board;
    }
}
