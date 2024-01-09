package Board;

import Board.Pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Board extends JPanel {
    final int length = 720;
    Color white = new Color(238, 238, 210, 255);
    Color black = new Color(118, 150, 86, 255);

    Knight knight = new Knight(true, length, 5,5);
    King king = new King(false, length, 2,2);
    King king2 = new King(true, length, 5,4);

    Bishop bishop = new Bishop(false, length, 4,4);
    Rook rook = new Rook(false, length, 0,0);
    Pawn pawn = new Pawn(true, length, 1,6);
    Pawn pawn2 = new Pawn(false, length, 2,1);
    Queen queen = new Queen(true, length, 7,7);

    Piece piece;

    ArrayList<Piece> pieces = new ArrayList<>();

    //Map<Point, Piece> pieces = new HashMap<>();

    Map<Point, Square> squares = new HashMap<>();



    Point previousPoint;

    public Board(){
        this.setPreferredSize(new Dimension(length,length));

        ClickListener clickListener = new ClickListener();
        this.addMouseListener(clickListener);

        DragListener dragListener = new DragListener();
        this.addMouseMotionListener(dragListener);

        for (int x = 0; x<8; x++){
            for (int y = 0; y<8; y++){
                squares.put(new Point(x,y), new Square());
            }
        }

        pieces.add(knight);
        pieces.add(king);
        pieces.add(king2);
        pieces.add(bishop);
        pieces.add(rook);
        pieces.add(pawn);
        pieces.add(pawn2);
        pieces.add(queen);

        squares.get(knight.getCoordinates()).setPiece(knight);
        squares.get(king.getCoordinates()).setPiece(king);
        squares.get(king2.getCoordinates()).setPiece(king2);
        squares.get(bishop.getCoordinates()).setPiece(bishop);
        squares.get(rook.getCoordinates()).setPiece(rook);
        squares.get(pawn.getCoordinates()).setPiece(pawn);
        squares.get(pawn2.getCoordinates()).setPiece(pawn2);
        squares.get(queen.getCoordinates()).setPiece(queen);

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

        for (Piece p : pieces){
            p.paint(this, g);
        }
    }

    private class ClickListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            for (Piece p : pieces){
                if (p.getBounds().contains(e.getPoint())){
                    piece = p;
                    piece.pressed(e);
                    previousPoint = e.getPoint();

                    repaint();
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            previousPoint = null;
            if (piece != null){
                piece.released(e, length, pieces, squares);
                piece = null;
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

                System.out.println((int)(currentPoint.getX()/(length/8)));
                System.out.println((int)(currentPoint.getY()/(length/8)));
                System.out.println(piece.getImageCorner());
                System.out.println(piece);

                previousPoint = currentPoint;

                repaint();
            }
        }
    }
}
