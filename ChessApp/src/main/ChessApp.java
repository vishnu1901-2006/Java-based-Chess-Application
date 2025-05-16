package main.ChessApp;

import gui.ChessGUI;

public class ChessApp {
    public static void main(String[] args) {
        // Start the Chess GUI on the Event Dispatch Thread for thread safety
        javax.swing.SwingUtilities.invokeLater(ChessGUI::new);
    }
}