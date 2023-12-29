import Board.Board;

import javax.swing.*;

public class Window extends JFrame {
    Window(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Chess");
        this.setLocationRelativeTo(null);

        Board board = new Board();
        this.add(board);
        this.pack();

        this.setVisible(true);
    }
}
