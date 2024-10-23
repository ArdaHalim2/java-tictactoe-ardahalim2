package se.iths.tictactoe.model;

public class TicTacToe {
    private char[] board; // Spelbrädet
    private char lastPlayer; // Senaste spelaren

    public TicTacToe() {
        board = new char[9];  // 9 positioner i brädet
        reset();  // Initiera med tomt bräde
    }

    // Gör ett drag på en viss position för en viss spelare
    public boolean makeMove(int position, char player) {
        if (board[position] == '-') {  // Kontrollera om platsen är tom
            board[position] = player;
            lastPlayer = player;
            return true;
        }
        return false;
    }

    // Kontrollera om någon har vunnit
    public boolean checkWin() {
        // Kontrollera alla vinnande kombinationer
        int[][] winningCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rader
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Kolumner
                {0, 4, 8}, {2, 4, 6} // Diagonaler
        };

        for (int[] combo : winningCombinations) {
            if (board[combo[0]] == lastPlayer &&
                    board[combo[0]] == board[combo[1]] &&
                    board[combo[1]] == board[combo[2]]) {
                return true; // Vinst om alla tre matchar
            }
        }
        return false;
    }

    // Kontrollera om brädet är fullt (oavgjort)
    public boolean isBoardFull() {
        for (char c : board) {
            if (c == '-') {
                return false;
            }
        }
        return true;
    }

    // Returnera aktuella tillståndet för brädet
    public char[] getBoard() {
        return board;
    }

    // Återställ brädet
    public void reset() {
        for (int i = 0; i < board.length; i++) {
            board[i] = '-';
        }
        lastPlayer = ' ';
    }
}
