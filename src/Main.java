import UX.Game;

import java.awt.*;


public
class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new Game();
        });
    }
}
