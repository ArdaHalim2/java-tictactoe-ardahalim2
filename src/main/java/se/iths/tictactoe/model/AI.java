package se.iths.tictactoe.model;

public class AI extends Player {
    public AI() {
        super('O'); // AI:s symbol är alltid O
    }

    //AI metod
    public int getBestMove(TicTacToe game) {
        int bestScore = Integer.MIN_VALUE; // Bästa poäng
        int move = -1; // Håll plats för bästa drag

        for (int i = 0; i < 9; i++) {
            if (game.getBoard()[i] == '-') { // Om rutan är tom
                game.makeMove(i); // Gör drag
                int score = minimax(game, 0, false); // Beräkna poäng
                game.getBoard()[i] = '-'; // Återställ draget
                if (score > bestScore) { // Om poängen är bättre
                    bestScore = score; // Uppdatera bästa poäng
                    move = i; // Spara bästa drag
                }
            }
        }
        return move; // Returnera bästa drag
    }

    // Minimax-algoritm
    private int minimax(TicTacToe game, int depth, boolean isMaximizing) {
        // Kontrollera om någon vunnit
        if (game.checkWin()) {
            return isMaximizing ? -1 : 1; // Vinstpoäng
        }
        if (game.isBoardFull()) {
            return 0; // Oavgjort
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (game.getBoard()[i] == '-') {
                    game.makeMove(i);
                    int score = minimax(game, depth + 1, false);
                    game.getBoard()[i] = '-'; // Återställ draget
                    bestScore = Math.max(score, bestScore);
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (game.getBoard()[i] == '-') {
                    game.makeMove(i);
                    int score = minimax(game, depth + 1, true);
                    game.getBoard()[i] = '-'; // Återställ draget
                    bestScore = Math.min(score, bestScore);
                }
            }
            return bestScore;
        }
    }

}
