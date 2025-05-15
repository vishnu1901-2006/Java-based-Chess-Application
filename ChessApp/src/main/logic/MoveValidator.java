package main.logic;

import main.board.Board;
import main.board.Square;
import main.pieces.Piece;

import java.util.List;

public class MoveValidator {

    public boolean isValidMove(Board board, Square startSquare, Square endSquare) {
        if (startSquare == null || endSquare == null || startSquare.isEmpty()) {
            return false;
        }

        Piece piece = startSquare.getPiece();
        List<Square> legalMoves = piece.getLegalMoves(board, startSquare);

        return legalMoves.contains(endSquare);
    }

    // You'll likely add more methods here for checking check, checkmate, etc.
}