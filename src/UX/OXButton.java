package UX;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OXButton extends JButton implements Colorable {
    Game.Field field;
    boolean empty = true;
    private Game.Turn value = Game.Turn.None;
    private final ActionListener actionListener;
    private final OXBoard oxBoard;
    public final int id;
    static private int lastId = 0;
    private boolean active = true;

    public OXButton(OXBoard oxBoard, Game.Field field) {
        this.id = lastId++;
        this.oxBoard = oxBoard;
        this.field = field;
        this.actionListener = e -> {
            if (empty && active) {
                value = oxBoard.getCurrentMove();
                setText(String.valueOf(value));
                empty = false;
                this.oxBoard.moveMade(field);
            }
        };

        setFocusPainted(false);
        setSize(75, 75);
        setContentAreaFilled(false);
        setOpaque(true);
        setFont(new Font("Arial", Font.BOLD, 30));
        addActionListener(actionListener);
    }

    public void setActive() {
        active = true;
    }

    public void setInactive() {
        active = false;
    }

    public Game.Turn getValue() {
        return value;
    }

    @Override
    public void setColor(Color color) {

        SwingUtilities.invokeLater(() -> {
//            System.out.println("SwingUtilities.invokeLater " + this.id);
            this.setBackground(color);
            this.repaint();
        });
    }
}
