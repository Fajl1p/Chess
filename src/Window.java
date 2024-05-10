import Board.BoardUI;

import javax.swing.*;

public class Window extends JFrame {
    Window(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Chess");
        this.setLocationRelativeTo(null);

        BoardUI boardUI = new BoardUI();
        this.add(boardUI);
        this.pack();

        this.setVisible(true);
    }
}
