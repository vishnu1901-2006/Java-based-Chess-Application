package main.logic;

import main.board.Square;

public class Move {
    private Square start;
    private Square end;

    public Move(Square start, Square end) {
        this.start = start;
        this.end = end;
    }

    public Square getStart() {
        return start;
    }

    public Square getEnd() {
        return end;
    }
}
