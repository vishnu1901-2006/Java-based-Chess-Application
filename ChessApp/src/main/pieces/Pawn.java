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
    public List<Square> getLegalMoves(Board board, Square startSquare) {
        List<Square> legalMoves = new ArrayList<>();
        int startRow = startSquare.getRow();
        int startCol = startSquare.getCol();
        int direction = (color == Color.WHITE) ? -1 : 1; // -1 for white (moves up), 1 for black (moves down)

        // 1. One step forward
        int targetRow = startRow + direction;
        int targetCol = startCol;
        if (isWithinBounds(targetRow, targetCol)) {
            Square oneStepForwardSquare = board.getSquare(targetRow, targetCol);
            if (oneStepForwardSquare != null && oneStepForwardSquare.isEmpty()) {
                legalMoves.add(oneStepForwardSquare);

                // 2. Two steps forward (only from starting row, and only if one step is also empty)
                int startingRow = (color == Color.WHITE) ? 6 : 1; // Row 6 for white, Row 1 for black
                if (startRow == startingRow) {
                    int twoStepsForwardRow = startRow + 2 * direction;
                    Square twoStepsForwardSquare = board.getSquare(twoStepsForwardRow, targetCol);
                    if (twoStepsForwardSquare != null && twoStepsForwardSquare.isEmpty()) {
                        legalMoves.add(twoStepsForwardSquare);
                    }
                }
            }
        }

        // 3. Captures (diagonal)
        // Check left diagonal capture
        int leftCaptureRow = startRow + direction;
        int leftCaptureCol = startCol - 1;
        if (isWithinBounds(leftCaptureRow, leftCaptureCol)) {
            Square leftCaptureSquare = board.getSquare(leftCaptureRow, leftCaptureCol);
            if (leftCaptureSquare != null && !leftCaptureSquare.isEmpty() && leftCaptureSquare.getPiece().getColor() != this.getColor()) {
                legalMoves.add(leftCaptureSquare);
            }
        }

        // Check right diagonal capture
        int rightCaptureRow = startRow + direction;
        int rightCaptureCol = startCol + 1;
        if (isWithinBounds(rightCaptureRow, rightCaptureCol)) {
            Square rightCaptureSquare = board.getSquare(rightCaptureRow, rightCaptureCol);
            if (rightCaptureSquare != null && !rightCaptureSquare.isEmpty() && rightCaptureSquare.getPiece().getColor() != this.getColor()) {
                legalMoves.add(rightCaptureSquare);
            }
        }

        // TODO: En Passant - This is complex and requires checking the *last* move made on the board.
        // It's not something a Piece can determine solely from Board and startSquare.
        // It would likely involve a check in GameState or MoveValidator based on the opponent's previous pawn move.

        // TODO: Pawn Promotion Eligibility - The moves added above already cover reaching the 8th rank.
        // The actual promotion logic (asking user for choice) will be handled in GameState.makeMove
        // after a pawn moves to the last rank.

        return legalMoves;
    }

    @Override
    public String toString() {
        return (color == Color.WHITE) ? "W_Pawn" : "B_Pawn";
    }
}
