package main.pieces;

import main.board.Board;
import main.board.Square;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public List<Square> getLegalMoves(Board board, Square startSquare) {
        List<Square> legalMoves = new ArrayList<>();
        int startRow = startSquare.getRow();
        int startCol = startSquare.getCol();

        // All 8 directions (including diagonals) - 1 square move
        int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}, // Up, Down, Left, Right
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1} // Diagonals
        };

        for (int[] dir : directions) {
            int targetRow = startRow + dir[0];
            int targetCol = startCol + dir[1];

            if (isWithinBounds(targetRow, targetCol)) {
                Square targetSquare = board.getSquare(targetRow, targetCol);
                if (targetSquare != null) { // Safeguard
                    if (targetSquare.isEmpty() || targetSquare.getPiece().getColor() != this.getColor()) {
                        // This is a pseudo-legal move for the king.
                        // The MoveValidator will later ensure this move doesn't put the king in check.
                        legalMoves.add(targetSquare);
                    }
                }
            }
        }

        // TODO: Castling - This is a special move that requires checking:
        // 1. King and rook have not moved from their initial squares.
        // 2. No pieces are between the king and the rook.
        // 3. The king is not currently in check.
        // 4. The squares the king moves through, and the square it lands on, are not attacked by opponent pieces.
        // This is typically handled by specific logic in GameState/MoveValidator that calls upon
        // helper methods in King or Board to check these conditions.
        // For example, you might add hasMoved flags to Piece, especially King and Rook.

        return legalMoves;
    }

    @Override
    public String toString() {
        return (color == Color.WHITE) ? "W_King" : "B_King";
    }
}
