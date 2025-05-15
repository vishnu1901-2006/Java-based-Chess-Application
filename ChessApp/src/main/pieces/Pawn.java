package main.pieces;

import main.board.Board;
import main.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color);
    }

    @Override
    public List<Square> getLegalMoves(Board board, Square currentSquare) {
        List<Square> legalMoves = new ArrayList<>();
        int row = currentSquare.getRow();
        int col = currentSquare.getCol();
        int direction = (getColor() == Color.WHITE) ? -1 : 1; // White moves up, black moves down

        // One step forward
        int nextRow = row + direction;
        if (isValidSquare(nextRow, col) && board.getSquare(nextRow, col).isEmpty()) {
            legalMoves.add(board.getSquare(nextRow, col));

            // Two steps forward (only on the first move)
            if ((getColor() == Color.WHITE && row == 6) || (getColor() == Color.BLACK && row == 1)) {
                int twoStepsRow = row + 2 * direction;
                if (isValidSquare(twoStepsRow, col) && board.getSquare(twoStepsRow, col).isEmpty()) {
                    legalMoves.add(board.getSquare(twoStepsRow, col));
                }
            }
        }

        // Capturing diagonally
        int captureLeftCol = col - 1;
        int captureRightCol = col + 1;
        if (isValidSquare(nextRow, captureLeftCol) && !board.getSquare(nextRow, captureLeftCol).isEmpty() &&
            board.getSquare(nextRow, captureLeftCol).getPiece().getColor() != getColor()) {
            legalMoves.add(board.getSquare(nextRow, captureLeftCol));
        }
        if (isValidSquare(nextRow, captureRightCol) && !board.getSquare(nextRow, captureRightCol).isEmpty() &&
            board.getSquare(nextRow, captureRightCol).getPiece().getColor() != getColor()) {
            legalMoves.add(board.getSquare(nextRow, captureRightCol));
        }

        // TODO: Implement en passant

        return legalMoves;
    }

    private boolean isValidSquare(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    @Override
    public String toString() {
        return (getColor() == Color.WHITE ? "W" : "B") + "_Pawn";
    }
}