// Filename: Bishop.java
package main.pieces;

import main.board.Board;
import main.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color);
    }

    @Override
    public List<Square> getLegalMoves(Board board, Square currentSquare) {
        List<Square> legalMoves = new ArrayList<>();
        int row = currentSquare.getRow();
        int col = currentSquare.getCol();

        // Directions: up-left, up-right, down-left, down-right
        int[][] directions = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        for (int[] dir : directions) {
            int currentRow = row + dir[0];
            int currentCol = col + dir[1];

            while (isValidSquare(currentRow, currentCol)) {
                Square targetSquare = board.getSquare(currentRow, currentCol);
                if (targetSquare.isEmpty()) {
                    legalMoves.add(targetSquare);
                } else {
                    if (targetSquare.getPiece().getColor() != getColor()) {
                        legalMoves.add(targetSquare); // Capture opponent's piece
                    }
                    break; // Stop in this direction after encountering a piece
                }
                currentRow += dir[0];
                currentCol += dir[1];
            }
        }

        return legalMoves;
    }

    private boolean isValidSquare(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    @Override
    public String toString() {
        return (getColor() == Color.WHITE ? "W" : "B") + "_Bishop";
    }
}
