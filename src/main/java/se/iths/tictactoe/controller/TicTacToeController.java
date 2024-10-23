package se.iths.tictactoe.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import se.iths.tictactoe.model.AI;
import se.iths.tictactoe.model.TicTacToe;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TicTacToeController {
    private TicTacToe game; // Spel-logik
    private AI ai; // AI motståndare
    private Button[] buttons; // Knappar för spelbrädet
    private int playerScore = 0; // Spelarens poäng
    private int aiScore = 0; // AI:s poäng

    @FXML
    private Label statusLabel; // Label för att visa status
    @FXML
    private Label scoreLabel; // Label för att visa poängställning
    @FXML
    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8; // Knappar för spelbrädet

    @FXML
    public void initialize() {
        game = new TicTacToe(); // Initiera spelet
        ai = new AI(); // Initiera AI:n
        buttons = new Button[]{button0, button1, button2, button3, button4, button5, button6, button7, button8};
        updateScoreLabel(); // Uppdatera poängen från början

        // Ställ in minsta storlek för knappar
        for (Button button : buttons) {
            button.setMinSize(100, 100);
        }
    }

    // Hantera knappklick från spelaren
    @FXML
    private void handleButtonClick(javafx.event.ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        int position = Integer.parseInt(clickedButton.getId().replace("button", ""));

        if (game.makeMove(position, 'X')) {  // Spelaren gör sitt drag
            clickedButton.setText("X");

            if (game.checkWin()) {  // Kontrollera om spelaren vunnit
                showAlert("Du vann!");
                updateScore('X');
                resetBoard();
                return;
            } else if (game.isBoardFull()) {  // Kontrollera oavgjort
                showAlert("Spelet är oavgjort!");
                resetBoard();
                return;
            }

            ai.makeAIMove(game);  // Låt AI:n göra sitt drag
            updateBoard();  // Uppdatera brädet med AI:s drag

            if (game.checkWin()) {  // Kontrollera om AI:n vann
                showAlert("AI:n vann!");
                updateScore('O');
                resetBoard();
                return;
            } else if (game.isBoardFull()) {  // Kontrollera oavgjort efter AI:s drag
                showAlert("Spelet är oavgjort!");
                resetBoard();
            }
        }
    }

    // Uppdatera knapparna på brädet efter AI:s drag
    private void updateBoard() {
        char[] board = game.getBoard();
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 'O') {
                buttons[i].setText("O");
            }
        }
    }

    // Visa ett meddelande när spelet är klart
    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Spelresultat");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Uppdatera poäng för spelaren och AI:n
    private void updateScore(char winner) {
        if (winner == 'X') {
            playerScore++;
        } else {
            aiScore++;
        }
        updateScoreLabel();
    }

    // Uppdatera poängvisning på skärmen
    private void updateScoreLabel() {
        scoreLabel.setText("Spelare: " + playerScore + " | AI: " + aiScore);
    }

    // Återställ spelbrädet för en ny match
    private void resetBoard() {
        game.reset();
        for (Button button : buttons) {
            button.setText("");
        }
        statusLabel.setText("Ny match påbörjad!");
    }
}
