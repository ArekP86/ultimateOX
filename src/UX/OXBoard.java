package UX;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OXBoard extends JPanel {

    final static String OXBOARD = "OXBOARD";
    final static String RESOLVEDVALUE = "RESOLVEDVALUE";
    private final OXBoard oxBoard = this;
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel boardPanel = new JPanel();
    private final JPanel resolvedValuePanel = new JPanel();
    private final JLabel valueLabel = new JLabel("");
    private final Game.Field field;
    private boolean resolved = false;
    private Game.Turn value = Game.Turn.None;
    private final UltimateBoard ultimateBoard;
    private final OXButton[][] oxButton = new OXButton[3][3];


    OXBoard(UltimateBoard ultimateBoard, Game.Field field) {
        this.ultimateBoard = ultimateBoard;
        this.field = field;
        boardPanel.setLayout(new GridLayout(0, 3));
        boardPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        int fieldInt = 0;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                oxButton[x][y] = new OXButton(this, Game.Field.values()[fieldInt++]);
                boardPanel.add(oxButton[x][y]);
            }
        }

        valueLabel.setFont(new Font("Arial", Font.BOLD, 100));
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseEntered(e);
                cardLayout.show(oxBoard, OXBOARD);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseExited(e);
                cardLayout.show(oxBoard, RESOLVEDVALUE);
            }
        });
        resolvedValuePanel.setLayout(new BorderLayout());
        resolvedValuePanel.add(valueLabel, BorderLayout.CENTER);

        this.setLayout(cardLayout);
        this.add(boardPanel, OXBOARD);
        this.add(resolvedValuePanel, RESOLVEDVALUE);
    }

    public void setActive() {
        if (resolved) {
            return;
        }
        SwingUtilities.invokeLater(() -> {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    this.oxButton[x][y].setBackground(Color.white);
                    this.oxButton[x][y].setActive();
                }
            }
        });
    }

    public void setInactive() {
        SwingUtilities.invokeLater(() -> {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    this.oxButton[x][y].setBackground(Color.lightGray);
                    this.oxButton[x][y].setInactive();
                }
            }
        });
    }

    public void moveMade(Game.Field field) {
        checkBoard();
        ultimateBoard.moveMade(this.field, field);
    }

    private void checkBoard() {
        for (int x = 0; x < 3; x++) {
            boolean rowO = true;
            boolean rowX = true;
            boolean colO = true;
            boolean colX = true;
            for (int y = 0; y < 3; y++) {
                if (Game.Turn.o != oxButton[x][y].getValue()) rowO = false;
                if (Game.Turn.x != oxButton[x][y].getValue()) rowX = false;
                if (Game.Turn.o != oxButton[y][x].getValue()) colO = false;
                if (Game.Turn.x != oxButton[y][x].getValue()) colX = false;
            }
            if (rowO || colO) {
                resolve(Game.Turn.o);
                return;
            }
            if (rowX || colX) {
                resolve(Game.Turn.x);
                return;
            }
        }
    }

    private void resolve(Game.Turn value) {
        this.value = value;
        this.resolved = true;
        valueLabel.setText(value.toString());
        cardLayout.show(this, RESOLVEDVALUE);
    }

    public Game.Turn getValue() {
        return value;
    }

    public Game.Turn getCurrentMove() {
        return ultimateBoard.getCurrentMove();
    }

    public boolean getResolved() {
        return resolved;
    }
}