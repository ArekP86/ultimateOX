package UX;

import java.awt.*;

public class ColorChanger {

    private final OXBoard board;
    private final OXButton button;
    private final Color color;
    private final String text;

    public ColorChanger(OXBoard board, OXButton button, Color color, String text) {
        this.board = board;
        this.button = button;
        this.color = color;
        this.text = text;
    }

    public void start() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("RUN " + color);
                button.setColor(color);
                button.setText(text);
                button.revalidate();
                button.repaint();
                board.revalidate();
                board.repaint();
            }
        });
        t.start();
    }
    /*

     */

}