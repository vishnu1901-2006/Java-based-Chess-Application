package main.pieces;

import main.board.Board;
import main.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color);
    }

    @Override
    public List<Square> getLegalMoves(Board board, Square currentSquare) {
        List<Square> legalMoves = new ArrayList<>();
        int row = currentSquare.getRow();
        int col = currentSquare.getCol();

        int[][] possibleMoves = {
                {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
                {1, -2}, {1, 2}, {2, -1}, {2, 1}
        };

        for (int[] move : possibleMoves) {
            int newRow = row + move[0];
            int newCol = col + move[1];

            if (isValidSquare(newRow, newCol)) {
                Square targetSquare = board.getSquare(newRow, newCol);
                if (targetSquare.isEmpty() || targetSquare.getPiece().getColor() != getColor()) {
                    legalMoves.add(targetSquare);
                }
            }
        }

        return legalMoves;
    }

    private boolean isValidSquare(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    @Override
    public String toString() {
        return (getColor() == Color.WHITE ? "W" : "B") + "_Knight";
    }
}