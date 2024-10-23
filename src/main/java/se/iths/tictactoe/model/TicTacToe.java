
package se.iths.tictactoe.model;

import java.util.Arrays;

public class TicTacToe {
    private final char[] board; // Spelbrädet
    private char lastPlayer; // Senaste spelaren

    public TicTacToe() {
        board = new char[9];
        reset();
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

    public boolean checkWin() {
        char[] board = getBoard();
        // Vinnande kombinationer
        int[][] winConditions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Horisontella
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Vertikala
                {0, 4, 8}, {2, 4, 6}             // Diagonaler
        };

        for (int[] condition : winConditions) {
            if (board[condition[0]] != '-' &&
                    board[condition[0]] == board[condition[1]] &&
                    board[condition[1]] == board[condition[2]]) {
                return true; // En vinnande kombination hittad
            }
        }
        return false; // Ingen vinnande kombination hittad
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
        Arrays.fill(board, '-');
        lastPlayer = ' ';
    }

    public char getLastPlayer() {
        return lastPlayer;
    }

    public void setLastPlayer(char lastPlayer) {
        this.lastPlayer = lastPlayer;
    }
}
