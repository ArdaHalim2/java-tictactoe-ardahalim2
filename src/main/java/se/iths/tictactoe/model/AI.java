package se.iths.tictactoe.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AI extends Player {
    public AI() {
        super('O'); // AI:s symbol är alltid O
    }

    // Gör ett drag för AI baserat på strategier
    public void makeAIMove(TicTacToe game) {
        char[] board = game.getBoard();

        // Försök att vinna
        for (int i = 0; i < board.length; i++) {
            if (board[i] == '-') {
                board[i] = 'O';  // Lägger temporärt O för att simulera AI:s drag
                if (game.checkWin()) {
                    return;
                }
                board[i] = '-';  // Återställ brädet
            }
        }

        // Blockera spelarens vinnande drag
        for (int i = 0; i < board.length; i++) {
            if (board[i] == '-') {
                board[i] = 'X';  // Lägger temporärt X för att simulera spelarens drag
                if (game.checkWin()) {
                    board[i] = 'O';  // Blockera spelarens vinnande drag
                    return;
                }
                board[i] = '-';  // Återställ brädet
            }
        }

        // Om ingen blockering behövs, gör ett slumpmässigt drag
        List<Integer> availablePositions = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            if (board[i] == '-') {
                availablePositions.add(i);  // Samla alla lediga positioner
            }
        }

        if (!availablePositions.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(availablePositions.size());
            int position = availablePositions.get(randomIndex);
            board[position] = 'O';  // AI gör sitt drag
        }
    }
}
