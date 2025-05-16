package main.pieces;

import main.board.Board;
import main.board.Square;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public List<Square> getLegalMoves(Board board, Square startSquare) {
        List<Square> legalMoves = new ArrayList<>();
        int startRow = startSquare.getRow();
        int startCol = startSquare.getCol();

        // All 8 L-shaped moves for a knight
        int[][] knightMoves = {
            {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, // Two steps in one direction, one step in perpendicular
            {1, -2}, {1, 2}, {2, -1}, {2, 1}
        };

        for (int[] move : knightMoves) {
            int targetRow = startRow + move[0];
            int targetCol = startCol + move[1];

            if (isWithinBounds(targetRow, targetCol)) {
                Square targetSquare = board.getSquare(targetRow, targetCol);
                if (targetSquare != null) { // Safeguard
                    if (targetSquare.isEmpty() || targetSquare.getPiece().getColor() != this.getColor()) {
                        legalMoves.add(targetSquare);
                    }
                }
            }
        }
        return legalMoves;
    }

    @Override
    public String toString() {
        return (color == Color.WHITE) ? "W_Knight" : "B_Knight";
    }
}
