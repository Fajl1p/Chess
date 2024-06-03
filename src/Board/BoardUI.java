package Board;

import Board.Pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class BoardUI extends JPanel {
    final int length = 720;
    Color white = new Color(238, 238, 210, 255);
    Color black = new Color(118, 150, 86, 255);

    Board board;

    Piece piece;

    Point previousPoint;

    boolean isPromoting = false;

    public BoardUI(){
        this.setPreferredSize(new Dimension(length,length));

        ClickListener clickListener = new ClickListener();
        this.addMouseListener(clickListener);

        DragListener dragListener = new DragListener();
        this.addMouseMotionListener(dragListener);

        board = new Board(length);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        board.paintBoard(g, white, black);
        board.paintPieces(this, g, isPromoting);

    }

    private class ClickListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);

            if (!isPromoting){
                for (Piece p : board.getPieces()){
                    if (p.getBounds().contains(e.getPoint())){
                        piece = p;
                        piece.pressed(e);
                        previousPoint = e.getPoint();

                        repaint();
                        break;
                    }
                }
            }
            else{
                for (Piece p : board.getProPieces()){
                    if (p.getBounds().contains(e.getPoint())){

                        board.promote(piece, p);
                        isPromoting = false;
                        piece = null;
                        repaint();
                        break;
                    }
                }
            }

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            previousPoint = null;
            if (piece != null){
                piece.released(e, board);
                isPromoting = piece.isPromoting();

                //board.getPieces().remove(piece);
                //board.getSquares().get(piece.getCoordinates()).setPiece(null);
                board.updateSquares();
                //board.getPieces().add(piece);
                //board.getSquares().get(piece.getCoordinates()).setPiece(piece);

                if (!isPromoting){
                    piece = null;
                }
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

                piece.getImageCorner().translate((int)(currentPoint.getX() - previousPoint.getX()),
                                       (int)(currentPoint.getY() - previousPoint.getY()));

                /*System.out.println((int)(currentPoint.getX()/(length/8)));
                System.out.println((int)(currentPoint.getY()/(length/8)));
                System.out.println(piece.getImageCorner());
                System.out.println(piece);*/

                previousPoint = currentPoint;

                repaint();
            }
        }
    }
}
