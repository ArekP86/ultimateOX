package UX;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    public enum Turn {o, x, None}

    public enum Field {NW, N, NE, W, C, E, SW, S, SE}

    Turn starts;
    Turn currentMove;
    private final JPanel main = new JPanel();
    UltimateBoard ultimateBoard;
    private final History history = new History();
    JButton anotherRoundButton = new JButton("start new round");
    SidePanel sidePanel = new SidePanel(history, anotherRoundButton);

    public Game() {
        super("Ultimate Tic Tac Toe by Lukasz P.");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        starts = Turn.o;
        currentMove = starts;
        sidePanel.setStartingMove(starts);
        sidePanel.setCurrentMove(currentMove);

        main.setLayout(new BorderLayout());

        ultimateBoard = new UltimateBoard(this);
        main.add(this.ultimateBoard, BorderLayout.CENTER);
        main.add(sidePanel, BorderLayout.EAST);

        anotherRoundButton.addActionListener(e -> {
            newRound();
            anotherRoundButton.setVisible(false);
        });
        anotherRoundButton.setVisible(false);

        main.setVisible(true);

        add(main);
        setSize(1280, 720);
        setVisible(true);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int answer = JOptionPane.showConfirmDialog(null,
                        "Exit?", "Enough?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (JOptionPane.YES_OPTION == answer) {
                    System.exit(0);
                }
            }
        });
    }

    private void newRound() {
        if (Turn.o == starts) {
            starts = Turn.x;
        } else {
            starts = Turn.o;
        }
        history.clear();
        main.remove(this.ultimateBoard);
        this.ultimateBoard = new UltimateBoard(this);
        main.add(this.ultimateBoard, BorderLayout.CENTER);
        main.revalidate();
        main.repaint();
    }


    public void moveMade(Field boardField, Field field) {
        history.addEntry(currentMove, boardField, field);
        if (Turn.o == currentMove) {
            currentMove = Turn.x;
        } else {
            currentMove = Turn.o;
        }
        sidePanel.setCurrentMove(currentMove);
        if (boardField == field) {
            ultimateBoard.activateAllOXBoards();
        } else {
            ultimateBoard.activateOXBoard(field);
        }
    }

    public void point(Turn value) {
        sidePanel.point(value);
        ultimateBoard.deactivateAllOXBoards();
        anotherRoundButton.setVisible(true);
    }

    public Turn getCurrentMove() {
        return currentMove;
    }
}
