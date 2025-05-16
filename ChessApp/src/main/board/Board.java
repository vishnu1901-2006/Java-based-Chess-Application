package main.board;

import main.pieces.Piece;

public class Board {
    private Square[][] squares;

    public Board() {
        squares = new Square[8][8];
        initializeBoard();
    }

    private void initializeBoard() {
        // Initialize squares
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                squares[row][col] = new Square(row, col);
            }
        }

        // Place pieces - initial setup
        placeInitialPieces();
    }

    private void placeInitialPieces() {
        // White pieces
        squares[7][0].setPiece(new main.pieces.Rook(Piece.Color.WHITE));
        squares[7][1].setPiece(new main.pieces.Knight(Piece.Color.WHITE));
        squares[7][2].setPiece(new main.pieces.Bishop(Piece.Color.WHITE));
        squares[7][3].setPiece(new main.pieces.Queen(Piece.Color.WHITE));
        squares[7][4].setPiece(new main.pieces.King(Piece.Color.WHITE));
        squares[7][5].setPiece(new main.pieces.Bishop(Piece.Color.WHITE));
        squares[7][6].setPiece(new main.pieces.Knight(Piece.Color.WHITE));
        squares[7][7].setPiece(new main.pieces.Rook(Piece.Color.WHITE));
        for (int i = 0; i < 8; i++) {
            squares[6][i].setPiece(new main.pieces.Pawn(Piece.Color.WHITE));
        }

        // Black pieces
        squares[0][0].setPiece(new main.pieces.Rook(Piece.Color.BLACK));
        squares[0][1].setPiece(new main.pieces.Knight(Piece.Color.BLACK));
        squares[0][2].setPiece(new main.pieces.Bishop(Piece.Color.BLACK));
        squares[0][3].setPiece(new main.pieces.Queen(Piece.Color.BLACK));
        squares[0][4].setPiece(new main.pieces.King(Piece.Color.BLACK));
        squares[0][5].setPiece(new main.pieces.Bishop(Piece.Color.BLACK));
        squares[0][6].setPiece(new main.pieces.Knight(Piece.Color.BLACK));
        squares[0][7].setPiece(new main.pieces.Rook(Piece.Color.BLACK));
        for (int i = 0; i < 8; i++) {
            squares[1][i].setPiece(new main.pieces.Pawn(Piece.Color.BLACK));
        }
    }

    public Square getSquare(int row, int col) {
        if (row >= 0 && row < 8 && col >= 0 && col < 8) {
            return squares[row][col];
        }
        return null; // Handle out of bounds
    }
    // Only to be used by validated move logic (e.g., GameState)
void movePiece(Square startSquare, Square endSquare) {
    if (startSquare == null || endSquare == null) return;
    Piece pieceToMove = startSquare.getPiece();
    if (pieceToMove != null) {
        endSquare.setPiece(pieceToMove);
        startSquare.setPiece(null);
    }
}
    

    // You'll likely add methods here for checking for checkmate, stalemate, etc.
}
