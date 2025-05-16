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
    public List<Square> getLegalMoves(Board board, Square startSquare, Move lastMove) {
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

        // 4. En Passant Logic
        if (lastMove != null) {
            Piece movedPiece = lastMove.getMovedPiece();
            int startRowOfLastMove = lastMove.getStartSquare().getRow();
            int endRowOfLastMove = lastMove.getEndSquare().getRow();
            int startColOfLastMove = lastMove.getStartSquare().getCol();
            int endColOfLastMove = lastMove.getEndSquare().getCol();

            // Check if last move was a pawn moving two steps
            if (movedPiece instanceof Pawn && Math.abs(startRowOfLastMove - endRowOfLastMove) == 2) {
                // Check if the opponent pawn is adjacent horizontally to this pawn
                if (endRowOfLastMove == startRow && Math.abs(endColOfLastMove - startCol) == 1) {
                    // The square behind the opponent pawn (where the pawn can move to capture en passant)
                    int enPassantCaptureRow = startRow + direction;
                    int enPassantCaptureCol = endColOfLastMove;
                    if (isWithinBounds(enPassantCaptureRow, enPassantCaptureCol)) {
                        Square enPassantSquare = board.getSquare(enPassantCaptureRow, enPassantCaptureCol);
                        if (enPassantSquare != null && enPassantSquare.isEmpty()) {
                            legalMoves.add(enPassantSquare);
                        }
                    }
                }
            }
        }

        return legalMoves;
    }

    @Override
    public String toString() {
        return (color == Color.WHITE) ? "W_Pawn" : "B_Pawn";
    }

    private boolean isWithinBounds(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
}
