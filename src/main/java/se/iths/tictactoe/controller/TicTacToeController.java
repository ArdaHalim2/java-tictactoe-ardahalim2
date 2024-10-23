package se.iths.tictactoe.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import se.iths.tictactoe.model.AI;
import se.iths.tictactoe.model.TicTacToe;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TicTacToeController {
    private TicTacToe game;
    private AI ai;
    private Button[] buttons;
    private int playerScore = 0;
    private int aiScore = 0;

    @FXML
    private Label statusLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8;

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

        // Kontrollera om spelaren kan göra sitt drag
        if (game.makeMove(position, 'X')) {  // Spelaren gör sitt drag
            clickedButton.setText("X"); // Uppdatera knappens text till "X"

            // Kontrollera om spelaren har vunnit
            if (game.checkWin()) {
                showAlert("Du vann!");
                updateScore('X');
                resetBoard();
                return;
            }

            // Kontrollera om brädet är fullt (oavgjort)
            if (game.isBoardFull()) {
                showAlert("Spelet är oavgjort!");
                resetBoard(); // Återställ brädet
                return;
            }
            // AI:n gör sitt drag
            ai.makeAIMove(game);
            updateBoard();

            // Kontrollera om AI:n har vunnit
            if (game.checkWin()) {
                showAlert("AI:n vann!");
                updateScore('O');
                resetBoard();
                return;
            }

            // Kontrollera om brädet är fullt efter AI:s drag (oavgjort)
            if (game.isBoardFull()) {
                showAlert("Spelet är oavgjort!");
                resetBoard();
            }
        } else {
            // Om draget var ogiltigt kan vi ge feedback till spelaren (valfritt)
            System.out.println("Ogiltigt drag av spelaren på position: " + position);
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
