package main.pieces;

import main.board.Board;
import main.board.Square;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public List<Square> getLegalMoves(Board board, Square startSquare) {
        List<Square> legalMoves = new ArrayList<>();
        int startRow = startSquare.getRow();
        int startCol = startSquare.getCol();

        // Directions: Up-Right, Up-Left, Down-Right, Down-Left
        int[][] directions = {{-1, 1}, {-1, -1}, {1, 1}, {1, -1}};

        for (int[] dir : directions) {
            int rowDir = dir[0];
            int colDir = dir[1];

            for (int i = 1; i < 8; i++) { // Iterate up to 7 squares in each direction
                int targetRow = startRow + i * rowDir;
                int targetCol = startCol + i * colDir;

                if (!isWithinBounds(targetRow, targetCol)) {
                    break; // Out of bounds, stop checking in this direction
                }

                Square targetSquare = board.getSquare(targetRow, targetCol);
                 if (targetSquare == null) { // Safeguard
                    break;
                }

                if (targetSquare.isEmpty()) {
                    legalMoves.add(targetSquare);
                } else {
                    if (targetSquare.getPiece().getColor() != this.getColor()) {
                        legalMoves.add(targetSquare); // Capture opponent's piece
                    }
                    break; // Blocked by own piece or captured opponent's piece, stop checking in this direction
                }
            }
        }
        return legalMoves;
    }

    @Override
    public String toString() {
        return (color == Color.WHITE) ? "W_Bishop" : "B_Bishop";
    }
}
