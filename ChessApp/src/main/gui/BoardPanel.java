package main.gui;

import main.board.Board;
import main.board.Square;
import main.pieces.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BoardPanel extends JPanel implements MouseListener {

    private final Board board;
    private final int SQUARE_SIZE = 70; // Adjust as needed
    private Square selectedSquare = null;
    private Map<String, Image> pieceImages;

    public BoardPanel(Board board) {
        this.board = board;
        setPreferredSize(new Dimension(8 * SQUARE_SIZE, 8 * SQUARE_SIZE));
        addMouseListener(this);
        loadPieceImages();
    }

    private void loadPieceImages() {
        pieceImages = new HashMap<>();
        try {
            pieceImages.put("W_Pawn", ImageIO.read(getClass().getResource("/resources/wpawn.png")));
            pieceImages.put("W_Rook", ImageIO.read(getClass().getResource("/resources/wrook.png")));
            pieceImages.put("W_Knight", ImageIO.read(getClass().getResource("/resources/wknight.png")));
            pieceImages.put("W_Bishop", ImageIO.read(getClass().getResource("/resources/wbishop.png")));
            pieceImages.put("W_Queen", ImageIO.read(getClass().getResource("/resources/wqueen.png")));
            pieceImages.put("W_King", ImageIO.read(getClass().getResource("/resources/wking.png")));
            pieceImages.put("B_Pawn", ImageIO.read(getClass().getResource("/resources/bpawn.png")));
            pieceImages.put("B_Rook", ImageIO.read(getClass().getResource("/resources/brook.png")));
            pieceImages.put("B_Knight", ImageIO.read(getClass().getResource("/resources/bknight.png")));
            pieceImages.put("B_Bishop", ImageIO.read(getClass().getResource("/resources/bbishop.png")));
            pieceImages.put("B_Queen", ImageIO.read(getClass().getResource("/resources/bqueen.png")));
            pieceImages.put("B_King", ImageIO.read(getClass().getResource("/resources/bking.png")));
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading piece images!", "Image Load Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Could not find piece images in resources!", "Resource Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // Draw the square
                Color squareColor = (row + col) % 2 == 0 ? new Color(240, 217, 181) : new Color(181, 136, 99); // Light and dark brown
                g.setColor(squareColor);
                g.fillRect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);

                // Draw the piece if it exists
                Piece piece = board.getSquare(row, col).getPiece();
                if (piece != null) {
                    Image pieceImage = pieceImages.get(piece.toString());
                    if (pieceImage != null) {
                        g.drawImage(pieceImage, col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE, this);
                    }
                }

                // Highlight the selected square
                if (selectedSquare != null && selectedSquare.getRow() == row && selectedSquare.getCol() == col) {
                    g.setColor(new Color(0, 255, 0, 100)); // Semi-transparent green
                    g.fillRect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int col = e.getX() / SQUARE_SIZE;
        int row = e.getY() / SQUARE_SIZE;

        Square clickedSquare = board.getSquare(row, col);

        if (selectedSquare == null) {
            // Select a piece
            if (clickedSquare != null && !clickedSquare.isEmpty()) {
                selectedSquare = clickedSquare;
                repaint();
            }
        } else {
            // Move the piece
            if (clickedSquare != null) {
                // Basic move implementation - needs validation
                board.movePiece(selectedSquare, clickedSquare);
                selectedSquare = null;
                repaint();
            } else {
                // Deselect if clicked outside the board (shouldn't happen with current setup)
                selectedSquare = null;
                repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Not needed for basic click selection
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Not needed for basic click selection
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Optional: could provide visual feedback on hover
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Optional: could remove hover feedback
    }
}