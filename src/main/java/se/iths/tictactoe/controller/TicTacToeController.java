package se.iths.tictactoe.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import se.iths.tictactoe.model.AI;
import se.iths.tictactoe.model.TicTacToe;

public class TicTacToeController {
    private TicTacToe game; // Spel-logik
    private AI ai; // AI motståndare
    private Button[] buttons; // Knappar för spelbrädet

    @FXML
    private Label statusLabel; // Label för att visa status
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
                statusLabel.setText("Spelare " + game.getCurrentPlayer().getSymbol() + " vinner!");
                return; // Avsluta spelet
            } else if (game.isBoardFull()) {
                statusLabel.setText("Spelet är oavgjort!");
                return; // Avsluta spelet
            }

            game.switchPlayer(); // Byt spelare

            // Låt AI göra sitt drag
            int aiMove = ai.getBestMove(game);
            game.makeMove(aiMove); // AI gör sitt drag
            buttons[aiMove].setText(String.valueOf(ai.getSymbol())); // Uppdatera knapptext för AI

            // Kontrollera vinst eller oavgjort efter AI:s drag
            if (game.checkWin()) {
                statusLabel.setText("AI " + ai.getSymbol() + " vinner!");
            } else if (game.isBoardFull()) {
                statusLabel.setText("Spelet är oavgjort!");
            }

            game.switchPlayer(); // Byt tillbaka till spelaren
        }
    }
}
