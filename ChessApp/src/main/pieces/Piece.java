package main.pieces;

import main.board.Board;
import main.board.Square;
import java.util.List;
import java.util.ArrayList; // Added for convenience in implementations

public abstract class Piece {

    public enum Color {
        WHITE, BLACK
    }

    protected Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    /**
     * Abstract method to get all pseudo-legal moves for a piece from a given square.
     * Pseudo-legal moves are those that follow the piece's basic movement patterns,
     * considering board boundaries and blocking by other pieces of the same color,
     * and capture of opponent pieces.
     *
     * IMPORTANT: This method should NOT check if the move leaves the player's own king in check.
     * That complex validation is handled by the MoveValidator class.
     *
     * @param board The current game board.
     * @param startSquare The square the piece is currently on.
     * @return A list of Squares representing all pseudo-legal moves.
     */
    public abstract List<Square> getLegalMoves(Board board, Square startSquare);

    // For debugging/display purposes (e.g., "W_Pawn", "B_King")
    @Override
    public abstract String toString();

    /**
     * Helper method to check if a square is within board boundaries.
     * @param row Row index.
     * @param col Column index.
     * @return true if within 0-7, false otherwise.
     */
    protected boolean isWithinBounds(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
}
