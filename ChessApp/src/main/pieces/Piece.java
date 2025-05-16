package main.pieces;

import main.board.Board;
import main.board.Square;

import java.util.List;

public abstract class Piece {
    public enum Color {
        WHITE,
        BLACK
    }

    private Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public abstract List<Square> getLegalMoves(Board board, Square currentSquare);

    @Override
    public abstract String toString(); // To represent the piece (e.g., "W_Pawn", "B_Rook")
}