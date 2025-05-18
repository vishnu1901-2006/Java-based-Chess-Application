package main.gui;

import main.board.Board;
import main.gui.BoardPanel;
import javax.swing.*;
import java.awt.*;

public class ChessGUI extends JFrame {
    private Board board;
    private BoardPanel boardPanel;
    private GameState gameState;
    public ChessGUI(GameState gameState) {
        this.gameState = gameState;
        setTitle("Chess Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new BorderLayout());

        board = new Board();
        boardPanel = new BoardPanel(board);
        add(boardPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChessGUI::new);
    }
}
