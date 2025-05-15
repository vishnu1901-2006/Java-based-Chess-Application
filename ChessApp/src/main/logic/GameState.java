package main.logic;

import main.board.Board;
import main.pieces.Piece;

public class GameState {
    private Board board;
    private Piece.Color currentPlayer;
    private MoveValidator moveValidator;

    public GameState(Board board) {
        this.board = board;
        this.currentPlayer = Piece.Color.WHITE; // White starts
        this.moveValidator = new MoveValidator();
    }

    public Board getBoard() {
        return board;
    }

    public Piece.Color getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == Piece.Color.WHITE) ? Piece.Color.BLACK : Piece.Color.WHITE;
    }

    public boolean makeMove(Square startSquare, Square endSquare) {
        if (moveValidator.isValidMove(board, startSquare, endSquare) &&
            (startSquare.getPiece().getColor() == currentPlayer)) {
            board.movePiece(startSquare, endSquare);
            switchPlayer();
            return true;
        }
        return false;
    }

    // Methods for checking game over conditions (checkmate, stalemate) will go here
}