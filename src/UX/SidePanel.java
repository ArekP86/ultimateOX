package UX;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {
    private int pointsO = 0;
    private int pointsX = 0;
    JLabel startingMoveLabel = new JLabel(" ");
    JLabel currentMoveLabel = new JLabel(" ");
    JLabel labelO = new JLabel("0");
    JLabel labelX = new JLabel("0");

    public SidePanel(History history, JButton newGameButton) {

        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.add(new Label("Round started by: "));
        startingMoveLabel.setFont(new Font("Arial", Font.BOLD, 20));
        header.add(startingMoveLabel);
        header.add(new JSeparator());
        header.add(new Label("Current player: "));
        header.add(currentMoveLabel);
        currentMoveLabel.setFont(new Font("Arial", Font.BOLD, 20));
        header.add(new JSeparator());
        header.add(new Label("Player O points:"));
        labelO.setFont(new Font("Arial", Font.BOLD, 20));
        header.add(labelO);
        header.add(new JSeparator());
        header.add(new Label("Player X points:"));
        labelX.setFont(new Font("Arial", Font.BOLD, 20));
        header.add(labelX);

        setLayout(new BorderLayout());
        add(header, BorderLayout.NORTH);
        add(history, BorderLayout.CENTER);
        add(newGameButton, BorderLayout.SOUTH);
    }

    public void point(Game.Turn value) {
        if (Game.Turn.o == value) {
            pointsO++;
            labelO.setText(String.valueOf(pointsO));
        } else {
            pointsX++;
            labelX.setText(String.valueOf(pointsX));
        }
    }

    public void setStartingMove(Game.Turn starts) {
        startingMoveLabel.setText(starts.toString());
    }

    public void setCurrentMove(Game.Turn currentMove) {
        currentMoveLabel.setText(currentMove.toString());
    }
}
