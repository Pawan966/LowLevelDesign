package TicTacToe.Dto;

public class Player {
    private final Piece piece;
    private final String name;

    public Player(Piece piece, String name) {
        this.piece = piece;
        this.name = name;
    }

    public Piece getPiece() {
        return piece;
    }

    public String getName() {
        return name;
    }
}
