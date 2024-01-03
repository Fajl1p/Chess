package Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


public class Board extends JPanel {
    final int length = 720;
    Color white = new Color(238, 238, 210, 255);
    Color black = new Color(118, 150, 86, 255);

    ImageIcon oldImage = new ImageIcon("./img/WhitePawn.png");
    ImageIcon image = new ImageIcon(oldImage.getImage().getScaledInstance(length/8, length/8,  java.awt.Image.SCALE_SMOOTH));  // transform it back


    Point image_corner;
    Point previousPoint;

    public Board(){
        this.setPreferredSize(new Dimension(length,length));

        image_corner = new Point(0,0);

        ClickListener clickListener = new ClickListener();
        this.addMouseListener(clickListener);

        DragListener dragListener = new DragListener();
        this.addMouseMotionListener(dragListener);

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

        image.paintIcon(this, g, (int)image_corner.getX(), (int)image_corner.getY());
    }

    private class ClickListener extends MouseAdapter{

        boolean pieceClicked = false;

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);

            Rectangle bounds = new Rectangle(0, 0, 0, 0);
            bounds.setLocation(image_corner);
            bounds.setSize(image.getIconWidth(), image.getIconHeight());

            if (bounds.contains(e.getPoint())){
                pieceClicked = true;

                image_corner.x = e.getX()-image.getIconWidth()/2;
                image_corner.y = e.getY()-image.getIconHeight()/2;

                previousPoint = e.getPoint();

                repaint();
            }
            else{
                pieceClicked = false;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            previousPoint = null;

            if (pieceClicked){
                image_corner.x = (e.getX()/(length/8))*(length/8);
                image_corner.y = (e.getY()/(length/8))*(length/8);

                repaint();
            }
        }
    }


    private class DragListener extends MouseMotionAdapter{
        @Override
        public void mouseDragged(MouseEvent e) {
            if (previousPoint != null){
                super.mouseDragged(e);
                Point currentPoint = e.getPoint();

                image_corner.translate((int)(currentPoint.getX() - previousPoint.getX()),
                                       (int)(currentPoint.getY() - previousPoint.getY()));

                /*System.out.println(currentPoint.getX() + " - " + previousPoint.getX());
                System.out.println(currentPoint.getY() + " - " + previousPoint.getY());
                System.out.println(image_corner);*/

                System.out.println((int)(currentPoint.getX()/(length/8)));
                System.out.println((int)(currentPoint.getY()/(length/8)));
                System.out.println(image_corner);

                previousPoint = currentPoint;

                repaint();
            }
        }
    }
}
