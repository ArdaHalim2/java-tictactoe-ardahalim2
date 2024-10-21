package se.iths.tictactoe.model;

public class Player {
    private char symbol; // Spelarens symbol

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
