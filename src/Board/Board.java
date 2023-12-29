package Board;

import javax.swing.*;
import java.awt.*;



public class Board extends JPanel {
    final int length = 720;
    Color white = new Color(238, 238, 210, 255);
    Color black = new Color(118, 150, 86, 255);

    public Board(){
        this.setPreferredSize(new Dimension(length,length));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (int x = 0; x<8; x++){
            for (int y = 0; y<8; y++){

                if (x%2==0 && y%2==0 || x%2!=0 && y%2!=0){
                    g.setColor(white);
                }else{
                    g.setColor(black);
                }

                g.fillRect(x*(length/8),y*(length/8),length/8,length/8);

            }
        }
    }
}
