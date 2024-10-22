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
        // Initiera spelet
        game = new TicTacToe();
        ai = new AI();
        buttons = new Button[]{button0, button1, button2, button3, button4, button5, button6, button7, button8};

        // Ställ in minsta storlek för knappar
        for (Button button : buttons) {
            button.setMinSize(100, 100);
        }

        updateScoreLabel(); // Uppdatera poängen från början
    }

    // Hantera knappklick
    @FXML
    private void handleButtonClick(javafx.event.ActionEvent event) {
        Button clickedButton = (Button) event.getSource(); // Hämta knappen som klickades
        int position = Integer.parseInt(clickedButton.getId().replace("button", "")); // Hämta position

        if (game.makeMove(position)) { // Om draget lyckas
            clickedButton.setText(String.valueOf(game.getCurrentPlayer().getSymbol())); // Sätt knapptext

            // Kontrollera vinst eller oavgjort
            if (game.checkWin()) {
                showAlert("Du vann!"); // Visa vem som vann
                updateScore(game.getCurrentPlayer().getSymbol()); // Uppdatera poäng för vinnaren
                resetBoard(); // Återställ brädet för nästa match
                return;
            } else if (game.isBoardFull()) {
                showAlert("Spelet är oavgjort!");
                resetBoard(); // Återställ brädet för nästa match
                return;
            }

            game.switchPlayer(); // Byt spelare
            // Låt AI göra sitt drag
            int aiMove = ai.getBestMove(game);
            game.makeMove(aiMove); // AI gör sitt drag
            buttons[aiMove].setText(String.valueOf(ai.getSymbol())); // Uppdatera knapptext för AI

            // Kontrollera vinst eller oavgjort efter AI:s drag
            if (game.checkWin()) {
                showAlert("AI:n vann!"); // Visa vem som vann
                updateScore(ai.getSymbol()); // Uppdatera poäng för AI
                resetBoard(); // Återställ brädet för nästa match
                return;
            } else if (game.isBoardFull()) {
                showAlert("Spelet är oavgjort!");
                resetBoard(); // Återställ brädet för nästa match
                return;
            }

            game.switchPlayer(); // Byt tillbaka till spelaren
        }
    }

    // Visar en Alert med resultatet
    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Spelresultat");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait(); // Vänta tills användaren stänger fönstret
    }

    // Uppdatera poäng för vinnaren
    private void updateScore(char winner) {
        if (winner == 'X') {
            playerScore++; // Spelaren vinner
        } else {
            aiScore++; // AI vinner
        }
        updateScoreLabel(); // Uppdatera poängvisning
    }

    // Uppdatera poängvisning på skärmen
    private void updateScoreLabel() {
        scoreLabel.setText("Spelare: " + playerScore + " | AI: " + aiScore);
    }

    // Återställ brädet efter en match
    private void resetBoard() {
        game.reset(); // Återställ spelets tillstånd
        for (Button button : buttons) {
            button.setText("");
            button.setDisable(false);// Rensa alla knapptexter
        }
        statusLabel.setText("Ny match påbörjad!"); // Statusmeddelande för ny match
    }
}
