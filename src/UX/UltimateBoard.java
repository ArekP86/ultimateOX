package UX;

import javax.swing.*;
import java.awt.*;

public class UltimateBoard extends JPanel {
    OXBoard[][] oxBoard = new OXBoard[3][3];
    Game game;
    private final Game.Turn value = Game.Turn.None;

    public UltimateBoard(Game game) {

        this.game = game;
        setLayout(new GridLayout(3, 3));
        int fieldInt = 0;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                oxBoard[x][y] = new OXBoard(this, Game.Field.values()[fieldInt++]);
                add(oxBoard[x][y]);
            }
        }
        setVisible(true);
    }

    public void moveMade(Game.Field boardField, Game.Field field) {
        if (checkUltimateBoard()) return;
        game.moveMade(boardField, field);
    }

    private boolean checkUltimateBoard() {
        for (int x = 0; x < 3; x++) {
            boolean rowO = true;
            boolean rowX = true;
            boolean colO = true;
            boolean colX = true;
            for (int y = 0; y < 3; y++) {
                if (Game.Turn.o != oxBoard[x][y].getValue()) rowO = false;
                if (Game.Turn.o != oxBoard[x][y].getValue()) rowX = false;
                if (Game.Turn.o != oxBoard[y][x].getValue()) colO = false;
                if (Game.Turn.o != oxBoard[y][x].getValue()) colX = false;
            }
            if (rowO || colO) {
                game.point(Game.Turn.o);
                return true;
            }
            if (rowX || colX) {
                game.point(Game.Turn.x);
                return true;
            }
        }
        return false;
    }

    public Game.Turn getCurrentMove() {
        return game.getCurrentMove();
    }

    public void activateOXBoard(Game.Field field) {
        int fieldCounter = 0;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (Game.Field.values()[fieldCounter] == field) {
                    if (oxBoard[y][x].getResolved()) {
                        activateAllOXBoards();
                        return;
                    }
                    oxBoard[y][x].setActive();
                } else {
                    oxBoard[y][x].setInactive();
                }
                fieldCounter++;
            }
        }
    }

    public void activateAllOXBoards() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                oxBoard[y][x].setActive();
            }
        }
    }

    public void deactivateAllOXBoards() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                oxBoard[y][x].setInactive();
            }
        }
    }
}
