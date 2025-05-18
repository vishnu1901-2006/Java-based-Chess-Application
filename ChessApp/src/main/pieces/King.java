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

       

       // Castling - King-side and Queen-side
if (!this.hasMoved()) {
    int row = startRow;
    Color opponentColor = this.getColor() == Color.WHITE ? Color.BLACK : Color.WHITE;

    // King-side castling
    Square kingSideRookSquare = board.getSquare(row, 7);
    if (kingSideRookSquare != null &&
        kingSideRookSquare.getPiece() instanceof Rook &&
        !((Rook) kingSideRookSquare.getPiece()).hasMoved()) {

        // Squares between king and rook must be empty
        boolean pathClear = true;
        for (int col = startCol + 1; col < 7; col++) {
            if (!board.getSquare(row, col).isEmpty()) {
                pathClear = false;
                break;
            }
        }

        // Squares king moves through must not be attacked and king can't be in check
        if (pathClear) {
            List<Square> kingPath = new ArrayList<>();
            kingPath.add(board.getSquare(row, 4)); // King's initial square
            kingPath.add(board.getSquare(row, 5));
            kingPath.add(board.getSquare(row, 6));
            boolean squaresSafe = true;
            for (Square sq : kingPath) {
                if (board.isSquareAttacked(sq, opponentColor)) {
                    squaresSafe = false;
                    break;
                }
            }
            if (squaresSafe) {
                legalMoves.add(board.getSquare(row, 6)); // King moves two squares toward rook
            }
        }
    }

    // Queen-side castling
    Square queenSideRookSquare = board.getSquare(row, 0);
    if (queenSideRookSquare != null &&
        queenSideRookSquare.getPiece() instanceof Rook &&
        !((Rook) queenSideRookSquare.getPiece()).hasMoved()) {

        // Squares between king and rook must be empty
        boolean pathClear = true;
        for (int col = startCol - 1; col > 0; col--) {
            if (!board.getSquare(row, col).isEmpty()) {
                pathClear = false;
                break;
            }
        }

        // Squares king moves through must not be attacked and king can't be in check
        if (pathClear) {
            List<Square> kingPath = new ArrayList<>();
            kingPath.add(board.getSquare(row, 4)); // King's initial square
            kingPath.add(board.getSquare(row, 3));
            kingPath.add(board.getSquare(row, 2));
            boolean squaresSafe = true;
            for (Square sq : kingPath) {
                if (board.isSquareAttacked(sq, opponentColor)) {
                    squaresSafe = false;
                    break;
                }
            }
            if (squaresSafe) {
                legalMoves.add(board.getSquare(row, 2)); // King moves two squares toward rook
            }
        }
    }
}
        return legalMoves;
    }

    @Override
    public String toString() {
        return (color == Color.WHITE) ? "W_King" : "B_King";
    }
}
