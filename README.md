
# Java-based-Chess_Applicaton
This repo has every Java file to make a complete application of chess. ♟️😊
## Team Name - PARALLEL MINDS
## Team Member's in this application building:
|S.R. NO.|Member's Name|
| --- | :---: |
| 1. | `Vishnukant Bajpai(Leader)` |
| 2. | `Piyush Sachan` |
| 3. | `Ankit Singh` |
| 4. | `Garv Anand` |

## **Chess Application in Java**
### Overview
`This project is a Java-based chess game application, designed to provide a classic chess experience with a graphical user interface (GUI). It's structured to be modular and extensible, making it suitable for further development and enhancements. This application was developed as a fun project and a way to apply object-oriented programming principles in Java.`
### Features:
  1. GUI Interface: A user-friendly graphical interface for playing chess.
  2. Standard Chess Rules: Implements the standard rules of chess, including piece movement, captures, and game-ending conditions.
  3. Object-Oriented Design: Cleanly structured using Java classes to represent the board, squares, pieces, and game logic.
  4. Move Validation: Ensures that only legal moves are allowed for each piece.
  5. Game State Management: Tracks the current state of the game, including whose turn it is and game over conditions.
  6. Piece Representation: Handles all standard chess pieces (Pawn, Rook, Knight, Bishop, Queen, King).
### Project Structure
The project is organized into the following packages: 
| --- |
|ChessApp/
├── src/
│   ├── main/
│   │   ├── ChessApp.java         (Main class)
│   │   ├── board/
│   │   │   ├── Board.java      (Represents the chessboard)
│   │   │   ├── Square.java     (Represents a single square on the board)
│   │   ├── pieces/
│   │   │   ├── Piece.java      (Abstract class for chess pieces)
│   │   │   ├── Pawn.java
│   │   │   ├── Rook.java
│   │   │   ├── Knight.java
│   │   │   ├── Bishop.java
│   │   │   ├── Queen.java
│   │   │   ├── King.java      (Specific implementations for each piece type)
│   │   ├── gui/
│   │   │   ├── ChessGUI.java     (Main GUI class)
│   │   │   ├── BoardPanel.java   (Panel for displaying the chessboard)
│   │   └── logic/
│   │       ├── MoveValidator.java (Validates the legality of moves)
│   │       └── GameState.java    (Manages the overall game state)
│
└── resources/                (Contains image files for the chess pieces)|
### How to Run
1. Prerequisites:
  - Java Development Kit (JDK) 8 or later.
2. Installation:
  - Clone the repository to your local machine.
  - Ensure that your IDE or build environment is set up to handle Java projects.
  - Place the chess piece image files in the `resources/` folder.
3. Compilation and Execution:
  - Using an IDE (IntelliJ IDEA, Eclipse, etc.):
      * Open the project in your IDE.
      * The IDE should automatically recognize the project structure and handle the dependencies.
      * Locate the `ChessApp.java` file in the `src/main/main/` directory.
      * Run the `ChessApp.java` file.
  - Using the command line:
      * Open a terminal or command prompt.
      * Navigate to the `src/main/` directory.
      * Compile the Java files: `javac *.java`.  (You might need to compile subdirectories separately or use wildcards carefully, depending on your shell). A safer approach is to compile from the main directory: `javac main/ChessApp.java`
      * Run the application: `java main.ChessApp`  (Make sure your current directory is the one containing the `main` folder.)
### Planned Enhancements
- Implement full game logic, including check, checkmate, and stalemate detection.
- Implement special moves like castling and en passant.
- Add support for pawn promotion.
- Improve the GUI with features like:
  * Move history.
  * Highlighted legal moves.
  * Visual enhancements.
- Add the ability to play against an AI opponent.
- Implement network play for playing against other users.
### Credits
> This project was created by [** PARALLEL MINDS **].
> Chess piece images are from [https://github.com/guy-jean/Personal-Projects/tree/9037d656645ad0b7e203c8ed7a56d18ddbb599d6/Java-Projects/Chess/pieces].
