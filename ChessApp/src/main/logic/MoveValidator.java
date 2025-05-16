package main.logic;

import main.board.Board;
import main.board.Square;
import main.pieces.Piece;

import java.util.List;

public class MoveValidator {

    /**
     * Checks if a move from the start square to the end square is valid on the given board.
     * @param board The current game board.
     * @param startSquare The square where the piece is moving from.
     * @param endSquare The square where the piece is moving to.
     * @return true if the move is valid, false otherwise.
     */
    public boolean isValidMove(Board board, Square startSquare, Square endSquare) {
        // Basic checks:
        // 1. Start square must not be empty.
        if (startSquare == null || startSquare.isEmpty()) {
            return false;
        }
        // 2. End square can be null, but if not null, check if it's the same as start
        if (endSquare != null && startSquare == endSquare) {
            return false;
        }

        Piece piece = startSquare.getPiece();
        // 3. Get the legal moves for the piece.
        List<Square> legalMoves = piece.getLegalMoves(board, startSquare);

        // 4. Check if the end square is in the list of legal moves.
        if (endSquare == null)
           return false;
        return legalMoves.contains(endSquare);
    }

    /**
     * Checks if the move from startSquare to endSquare would leave the moving player's king in check.
     * This method simulates the move, checks for check, and then undoes the move.
     * @param board The game board.
     * @param startSquare The starting square of the move.
     * @param endSquare The ending square of the move.
     * @param currentPlayer The color of the player making the move.
     * @return true if the move would result in the player's king being in check, false otherwise.
     */
    public boolean isKingInCheckAfterMove(Board board, Square startSquare, Square endSquare, Piece.Color currentPlayer) {
        // 1. Simulate the move:
        Piece movedPiece = startSquare.getPiece();
        Piece capturedPiece = endSquare.getPiece(); // Store any captured piece
        endSquare.setPiece(movedPiece);
        startSquare.setPiece(null);

        // 2. Find the king:
        Square kingSquare = findKing(board, currentPlayer);
        if (kingSquare == null) {
            // Should not happen, but handle it to prevent errors.
            //  System.err.println("Error: King not found!"); //It was printing error
            return true; // Consider it check to prevent illegal moves.
        }

        // 3. Check if the king is in check:
        boolean inCheck = isKingInCheck(board, kingSquare, currentPlayer);

        // 4. Undo the move:
        startSquare.setPiece(movedPiece);
        endSquare.setPiece(capturedPiece); // Restore captured piece
        return inCheck;
    }

    /**
     * Checks if the king at the given square is currently in check.
     * @param board The game board.
     * @param kingSquare The square where the king is located.
     * @param currentPlayer The color of the player whose king is being checked.
     * @return true if the king is in check, false otherwise.
     */
    private boolean isKingInCheck(Board board, Square kingSquare, Piece.Color currentPlayer) {
        // Iterate through all squares and check if any opponent's piece can attack the king.
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Square currentSquare = board.getSquare(row, col);
                if (!currentSquare.isEmpty()) {
                    Piece piece = currentSquare.getPiece();
                    // Check if it's an opponent's piece
                    if (piece.getColor() != currentPlayer) {
                        //Get the list of legal moves for this opponent piece
                        List<Square> opponentMoves = piece.getLegalMoves(board, currentSquare);
                        //If the king's square is one of the legal moves, the king is in check.
                        if (opponentMoves.contains(kingSquare)) {
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
    * @param board The game board.
    * @param playerColor The color of the player (whose king to find).
    * @return The Square where the king is located, or null if not found.
    */
    private Square findKing(Board board, Piece.Color playerColor) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Square square = board.getSquare(row, col);
                if (!square.isEmpty()) {
                    Piece piece = square.getPiece();
                    if (piece.getColor() == playerColor && piece instanceof main.pieces.King) {
                        return square;
                    }
                }
            }
        }
        return null;
    }
}
