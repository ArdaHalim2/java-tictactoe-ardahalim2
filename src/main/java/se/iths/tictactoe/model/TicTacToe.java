package se.iths.tictactoe.model;

public class TicTacToe {
    private char[] board; // Spelytan
    private Player currentPlayer; // Aktuell spelare

    public TicTacToe() {
        board = new char[9]; // Skapa en ny array
        currentPlayer = new Player('X'); // Sätter spelaren till X
        initializeBoard();
    }

    // Initierar spelytan med '-'
    public void initializeBoard() {
        for (int i = 0; i < 9; i++) {
            board[i] = '-';
        }
    }

    // Kontrollera om spelytan är full
    public boolean isBoardFull() {
        for (char cell : board) {
            if (cell != '-') {
                return false; // Finns tomma rutor
            }
        }
        return true; // Ogiltigt drag
    }

    public boolean makeMove(int position) {
        if (position >= 0 && position < 9 && board[position] == '-') {
            board[position] = currentPlayer.getSymbol(); // Sätt aktuella spelares symbol
            return true; // Draget lyckades
        }
        return false; // Ogiltigt drag
    }

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
            if (board[condition[0]] == currentPlayer.getSymbol() &&
                    board[condition[1]] == currentPlayer.getSymbol() &&
                    board[condition[2]] == currentPlayer.getSymbol()) {
                return true; // Vinst hittad
            }
        }
        return false;
    }

    public char[] getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
