package main.logic;

import main.board.Board;
import main.board.Square;
import main.pieces.Piece;
import main.pieces.King; // Ensure this import is present

import java.util.List;

public class MoveValidator {

    /**
     * Checks if a move from the start square to the end square is valid on the given board.
     * A move is valid if:
     * 1. The start square is not empty.
     * 2. The end square is a valid square on the board (not null).
     * 3. The end square is not the same as the start square.
     * 4. The target piece (if any) is not of the same color as the moving piece. (Handled by getLegalMoves)
     * 5. The move is pseudo-legal according to the piece's movement rules (handled by piece.getLegalMoves).
     * 6. The move does not leave the current player's king in check (handled by isKingInCheckAfterMove).
     *
     * @param board The current game board.
     * @param startSquare The square where the piece is moving from.
     * @param endSquare The square where the piece is moving to.
     * @return true if the move is valid, false otherwise.
     */
    public boolean isValidMove(Board board, Square startSquare, Square endSquare) {
        // Basic checks:
        if (startSquare == null || startSquare.isEmpty()) {
            return false; // Must have a piece to move
        }
        if (endSquare == null || startSquare == endSquare) {
            return false; // Cannot move to a null square or the same square
        }

        Piece piece = startSquare.getPiece();
        // Get the list of pseudo-legal moves for the piece.
        List<Square> pseudoLegalMoves = piece.getLegalMoves(board, startSquare);

        // Check if the end square is among the piece's pseudo-legal moves.
        if (!pseudoLegalMoves.contains(endSquare)) {
            return false; // The move is not valid based on piece's movement pattern
        }

        // Additional check: Ensure the target square isn't occupied by a piece of the same color
        if (!endSquare.isEmpty() && endSquare.getPiece().getColor() == piece.getColor()) {
            return false; // Cannot capture your own piece
        }

        // Finally, check if the move would leave the current player's king in check.
        // This is the most crucial validation step.
        return !isKingInCheckAfterMove(board, startSquare, endSquare, piece.getColor());
    }

    /**
     * Checks if the move from startSquare to endSquare would leave the moving player's king in check.
     * This method simulates the move, checks for check, and then undoes the move.
     *
     * @param board The game board.
     * @param startSquare The starting square of the move.
     * @param endSquare The ending square of the move.
     * @param currentPlayer The color of the player making the move.
     * @return true if the move would result in the player's king being in check, false otherwise.
     */
    public boolean isKingInCheckAfterMove(Board board, Square startSquare, Square endSquare, Piece.Color currentPlayer) {
        // Store original state to revert after simulation
        Piece movedPiece = startSquare.getPiece();
        Piece capturedPiece = endSquare.getPiece(); // Store any piece on the end square (could be null)

        // Simulate the move:
        endSquare.setPiece(movedPiece);
        startSquare.setPiece(null);

        // Find the king of the currentPlayer (whose turn it is)
        Square kingSquare = findKing(board, currentPlayer);

        // If king not found (should generally not happen in a valid game state),
        // we return true as a fail-safe to prevent potentially invalid states.
        if (kingSquare == null) {
            // For production, consider logging this as a severe error.
            return true; // Assume check to prevent issues
        }

        // Check if the king is in check after the simulated move:
        boolean inCheck = isKingInCheck(board, kingSquare, currentPlayer);

        // Undo the move:
        startSquare.setPiece(movedPiece);     // Restore the moved piece to its original square
        endSquare.setPiece(capturedPiece); // Restore any captured piece (or null) to the end square

        return inCheck;
    }

    /**
     * Checks if the king at the given square is currently in check.
     *
     * @param board The game board.
     * @param kingSquare The square where the king is located.
     * @param kingColor The color of the player whose king is being checked.
     * @return true if the king is in check, false otherwise.
     */
    public boolean isKingInCheck(Board board, Square kingSquare, Piece.Color kingColor) {
        // Iterate through all squares and check if any opponent's piece can attack the king.
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Square currentSquare = board.getSquare(row, col);
                // Ensure the square is valid and has a piece
                if (currentSquare != null && !currentSquare.isEmpty()) {
                    Piece piece = currentSquare.getPiece();
                    // Check if it's an opponent's piece
                    if (piece.getColor() != kingColor) {
                        // Get the list of pseudo-legal moves for this opponent piece.
                        // This list should include any squares the opponent's piece 'attacks'.
                        List<Square> opponentPseudoLegalMoves = piece.getLegalMoves(board, currentSquare);

                        // If the king's square is among the opponent's pseudo-legal moves, the king is in check.
                        if (opponentPseudoLegalMoves.contains(kingSquare)) {
                            return true; // King is in check
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Finds the square of the king for the given player.
     *
     * @param board The game board.
     * @param playerColor The color of the player (whose king to find).
     * @return The Square where the king is located, or null if not found.
     */
    private Square findKing(Board board, Piece.Color playerColor) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Square square = board.getSquare(row, col);
                // Ensure the square is valid and not empty before checking the piece
                if (square != null && !square.isEmpty()) {
                    Piece piece = square.getPiece();
                    if (piece.getColor() == playerColor && piece instanceof King) { // Use the imported King class
                        return square;
                    }
                }
            }
        }
        return null;
    }
}
