package se.iths.tictactoe.model;

import java.util.Arrays;

public class TicTacToe {
    private Character[] board; // Spelytan
    private Player currentPlayer; // Aktuell spelare

    public TicTacToe() {
        board = new Character[9]; // Skapa en ny array
        currentPlayer = new Player('X'); // Sätter spelaren till X
        initializeBoard(); // Initierar brädet
    }

    // Initierar spelytan med '-'
    public void initializeBoard() {
        Arrays.fill(board, '-'); // Fyller brädet med '-'
    }

    // Återställ spelplanen för en ny match
    public void reset(){
        initializeBoard(); // Använder initializeBoard för att återställa spelplanen
        currentPlayer = new Player('X'); // Återställer så att spelare X börjar
    }

    // Kontrollera om spelytan är full
    public boolean isBoardFull() {
        for (char cell : board) {
            if (cell == '-') { // Kontrollera om cellen är tom
                return false; // Det finns tomma celler, brädet är inte fullt
            }
        }
        return true; // Brädet är fullt
    }

    // Gör ett drag om rutan är tom
    public boolean makeMove(int position) {
        if (position >= 0 && position < 9 && board[position] == '-') {
            board[position] = currentPlayer.getSymbol(); // Sätt aktuella spelares symbol
            return true; // Draget lyckades
        }
        return false; // Ogiltigt drag
    }

    // Byter spelare
    public void switchPlayer() {
        currentPlayer = (currentPlayer.getSymbol() == 'X') ? new Player('O') : new Player('X'); // Byter mellan X och O
    }

    // Kontrollera om någon av spelarna har vunnit
    public boolean checkWin() {
        // Möjliga vinstkombinationer
        int[][] winConditions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rader
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Kolumner
                {0, 4, 8}, {2, 4, 6} // Diagonaler
        };

        for (int[] condition : winConditions) {
            if (board[condition[0]] != '-' && // Kontrollera att cellerna inte är tomma
                    board[condition[0]] == currentPlayer.getSymbol() &&
                    board[condition[1]] == currentPlayer.getSymbol() &&
                    board[condition[2]] == currentPlayer.getSymbol()) {
                return true; // Vinst hittad
            }
        }
        return false; // Ingen vinst
    }

    public Character[] getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
