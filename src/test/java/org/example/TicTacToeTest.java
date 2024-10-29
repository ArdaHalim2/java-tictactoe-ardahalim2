package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.iths.tictactoe.model.TicTacToe;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeTest {

    private TicTacToe game;

    @BeforeEach
    void setup(){
        game = new TicTacToe();
    }

    @Test
    void testIfMoveIsValid(){
        assertTrue(game.makeMove(0,'X'), "position 0 bör vara giltig");
        assertEquals('X', game.getBoard()[0], "positon 0 bör innehålla 'X'");
    }

    @Test
    void testIfMoveIsInvalid(){
        game.makeMove(0, 'X');
        assertFalse(game.makeMove(0, 'O'),"position O bör inta vara giltig efter första drag");
    }

    @Test
    void testHorizontalWin(){
        game.makeMove(0, 'X');
        game.makeMove(1, 'X');
        game.makeMove(2, 'X');
        assertTrue(game.checkWin(), "Spelare 'X' bör vinna horisontellt");
    }

    @Test
    void testVerticalWin(){
        game.makeMove(0, 'X');
        game.makeMove(3, 'X');
        game.makeMove(6, 'X');
        assertTrue(game.checkWin(), "Spelare 'X' bör vinna vertikalt");
    }

    @Test
    void testDiagonalWin(){
        game.makeMove(0, 'X');
        game.makeMove(4, 'X');
        game.makeMove(8, 'X');
        assertTrue(game.checkWin(), "Spelare 'X' bör vinna diagonalt");
    }

    @Test
    void testDraw() {
        game.makeMove(0, 'X');
        game.makeMove(1, 'O');
        game.makeMove(2, 'X');
        game.makeMove(3, 'X');
        game.makeMove(4, 'O');
        game.makeMove(5, 'X');
        game.makeMove(6, 'O');
        game.makeMove(7, 'X');
        game.makeMove(8, 'O');

        assertTrue(game.isBoardFull(), "Brädet bör vara fullt.");
        assertFalse(game.checkWin(), "Det bör inte finnas någon vinnare.");
    }
    @Test
    void testResetBoard() {
        game.makeMove(0, 'X');
        game.reset();

        for (char cell : game.getBoard()) {
            assertEquals('-', cell, "Alla positioner bör vara återställda till '-' efter reset.");
        }
        assertEquals(' ', game.getLastPlayer(), "Senaste spelaren bör vara blank efter reset.");
    }

}
