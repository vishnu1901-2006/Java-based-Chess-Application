package main.pieces;

import main.board.Board;
import main.board.Square;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public List<Square> getLegalMoves(Board board, Square startSquare) {
        List<Square> legalMoves = new ArrayList<>();
        int startRow = startSquare.getRow();
        int startCol = startSquare.getCol();

        // Queen moves like a Rook (horizontal and vertical)
        // Directions: Up, Down, Left, Right
        int[][] straightDirections = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : straightDirections) {
            int rowDir = dir[0];
            int colDir = dir[1];
            for (int i = 1; i < 8; i++) {
                int targetRow = startRow + i * rowDir;
                int targetCol = startCol + i * colDir;

                if (!isWithinBounds(targetRow, targetCol)) {
                    break;
                }

                Square targetSquare = board.getSquare(targetRow, targetCol);
                 if (targetSquare == null) { // Safeguard
                    break;
                }

                if (targetSquare.isEmpty()) {
                    legalMoves.add(targetSquare);
                } else {
                    if (targetSquare.getPiece().getColor() != this.getColor()) {
                        legalMoves.add(targetSquare);
                    }
                    break;
                }
            }
        }

        // Queen moves like a Bishop (diagonal)
        // Directions: Up-Right, Up-Left, Down-Right, Down-Left
        int[][] diagonalDirections = {{-1, 1}, {-1, -1}, {1, 1}, {1, -1}};
        for (int[] dir : diagonalDirections) {
            int rowDir = dir[0];
            int colDir = dir[1];
            for (int i = 1; i < 8; i++) {
                int targetRow = startRow + i * rowDir;
                int targetCol = startCol + i * colDir;

                if (!isWithinBounds(targetRow, targetCol)) {
                    break;
                }

                Square targetSquare = board.getSquare(targetRow, targetCol);
                if (targetSquare == null) { // Safeguard
                    break;
                }

                if (targetSquare.isEmpty()) {
                    legalMoves.add(targetSquare);
                } else {
                    if (targetSquare.getPiece().getColor() != this.getColor()) {
                        legalMoves.add(targetSquare);
                    }
                    break;
                }
            }
        }
        return legalMoves;
    }

    @Override
    public String toString() {
        return (color == Color.WHITE) ? "W_Queen" : "B_Queen";
    }
}
