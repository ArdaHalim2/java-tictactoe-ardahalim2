package se.iths.tictactoe.model;

public class TicTacToe {
    private char[] board; // Spelytan
    private Player currentPlayer; // Aktuell spelare

    public TicTacToe() {
        board = new char[9];
    }
}
