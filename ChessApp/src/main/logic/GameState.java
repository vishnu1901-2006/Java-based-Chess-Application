package main.logic;

import main.logic.Move;
import main.board.Board;
import main.board.Square;
import main.pieces.Piece;
import java.util.List;
import java.util.ArrayList;

public class GameState {
    private Board board;
    private Piece.Color currentPlayer;
    private MoveValidator moveValidator;
    private boolean gameOver;
    private Move lastMove;

    public GameState(Board board) {
        this.board = board;
        this.currentPlayer = Piece.Color.WHITE; // White starts
        this.moveValidator = new MoveValidator();
        this.gameOver = false;
    }

    public Board getBoard() {
        return board;
    }

    public Piece.Color getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameOver() {
        return gameOver;
    }
    public Move getLastMove() {
    return lastMove;
}

    /**
     * Attempts to make a move from the start square to the end square.
     * @param startSquare The starting square of the move.
     * @param endSquare The ending square of the move.
     * @return true if the move was successfully made, false otherwise.
     */
    public boolean makeMove(Square startSquare, Square endSquare) {
        if (gameOver) {
            return false; // Game is over, no moves allowed
        }

        if (startSquare == null || endSquare == null) {
            return false; // Handle null squares
        }
        // 1. Check if the move is valid according to the piece's movement rules AND
        //    if the piece at the start square belongs to the current player.
        if (moveValidator.isValidMove(board, startSquare, endSquare) &&
            startSquare.getPiece().getColor() == currentPlayer) {

            // 2. Check if the move would leave the current player's king in check.
            if (moveValidator.isKingInCheckAfterMove(board, startSquare, endSquare, currentPlayer)) {
                return false; // Move is illegal, cannot leave king in check.
            }

            // 3. Perform the move:
            board.movePiece(startSquare, endSquare);
            lastMove = new Move(startSquare, endSquare);

            // 4. Switch the current player.
            switchPlayer();

            // 5. Check for game over (checkmate or stalemate) after the move.
            if (isKingInCheck(board, currentPlayer))
            {
                if (hasNoLegalMoves(currentPlayer)) {
                    gameOver = true;
                    System.out.println("Checkmate! " + (currentPlayer == Piece.Color.WHITE ? "Black" : "White") + " wins!");
                }
            }
            else if (hasNoLegalMoves(currentPlayer)) {
                    gameOver = true;
                    System.out.println("Stalemate!");
            }

            return true; // Move was successful.
        }
        return false; // Move was invalid.
    }

    /**
     * Switches the current player.
     */
    private void switchPlayer() {
        currentPlayer = (currentPlayer == Piece.Color.WHITE) ? Piece.Color.BLACK : Piece.Color.WHITE;
    }



    /**
     * Checks if the current player's king is in check.
     * @param board The game board.
     * @param playerColor The color of the player to check.
     * @return true if the king is in check, false otherwise.
     */
    private boolean isKingInCheck(Board board, Piece.Color playerColor) {
        Square kingSquare = findKing(board, playerColor);
        if (kingSquare == null) {
            return false; //Should not happen
        }
        return moveValidator.isKingInCheck(board, kingSquare, playerColor);
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

    /**
     * Checks if the given player has any legal moves available.
     * @param playerColor The color of the player to check.
     * @return true if the player has no legal moves, false otherwise.
     */
     private boolean hasNoLegalMoves(Piece.Color playerColor) {
    for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
            Square square = board.getSquare(row, col);
            if (!square.isEmpty() && square.getPiece().getColor() == playerColor) {
                Piece piece = square.getPiece();
                List<Square> legalMoves = piece.getLegalMoves(board, square);
                for (Square move : legalMoves) {
                    if (!moveValidator.isKingInCheckAfterMove(board, square, move, playerColor)) {
                        return false; // Found a safe legal move
                    }
                }
            }
        }
    }
    return true; // No legal moves found
}
}


