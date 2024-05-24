package Board.Pieces;

import Board.Board;
import Board.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;

public class Piece {
    ImageIcon image;
    Point imageCorner;
    Point coordinates;
    Rectangle bounds;
    boolean isWhite;
    boolean isMoved = false;
    boolean isPromoting = false;

    Piece(boolean isWhite, int boardLength, int x, int y){
        coordinates = new Point(x, y);
        imageCorner = new Point(x*(boardLength/8), y*(boardLength/8));
        this.isWhite = isWhite;
        String directory;
        if (isWhite){
            directory = getDirectoryW();
        }
        else{
            directory = getDirectoryB();
        }

        ImageIcon oldIcon = new ImageIcon(directory);
        image = new ImageIcon(oldIcon.getImage().getScaledInstance(boardLength/8, boardLength/8,  java.awt.Image.SCALE_SMOOTH));

        bounds = new Rectangle(0, 0, 0, 0);
        bounds.setLocation(imageCorner);
        bounds.setSize(image.getIconWidth(), image.getIconHeight());
    }

    public Piece promote(Piece proPiece){
        return switch (proPiece) {
            case Queen ignored -> new Queen(isWhite, bounds.height * 8, coordinates.x, coordinates.y);
            case Rook ignored -> new Rook(isWhite, bounds.height * 8, coordinates.x, coordinates.y);
            case Bishop ignored -> new Bishop(isWhite, bounds.height * 8, coordinates.x, coordinates.y);
            default -> new Knight(isWhite, bounds.height * 8, coordinates.x, coordinates.y);
        };


    }

    public void updateImage(){
        String directory;
        if (isWhite){
            directory = getDirectoryW();
        }
        else{
            directory = getDirectoryB();
        }

        ImageIcon oldIcon = new ImageIcon(directory);
        image = new ImageIcon(oldIcon.getImage().getScaledInstance(image.getIconWidth(), image.getIconHeight(),  java.awt.Image.SCALE_SMOOTH));
    }

    public ImageIcon getImage(){
        return image;
    }

    public Point getImageCorner() {
        return imageCorner;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public boolean isPromoting() {
        return isPromoting;
    }



    public void setImageCorner(int x, int y) {
        this.imageCorner = new Point(x, y);
    }
    public void setImageCornerX(int x) {
        this.imageCorner.x = x;
    }
    public void setImageCornerY(int y) {
        this.imageCorner.y = y;
    }

    public void setCoordinates(int x, int y) {
        this.coordinates = new Point(x, y);
    }

    public void setX(int x) {
        this.coordinates.x = x;
    }

    public void setY(int y) {
        this.coordinates.y = y;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
    public void setBoundsX(int x) {
        this.bounds.x = x;
    }

    public void setBoundsY(int y) {
        this.bounds.y = y;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public void paint(Component c, Graphics g){
        image.paintIcon(c, g, imageCorner.x, imageCorner.y);
    }

    public void pressed(MouseEvent e){
        imageCorner.x = e.getX()-image.getIconWidth()/2;
        imageCorner.y = e.getY()-image.getIconHeight()/2;
    }

    public void released(MouseEvent e, Board board){
        ArrayList<Piece> pieces = board.getPieces();
        Map<Point, Square> squares = board.getSquares();
        ArrayList<Piece> proPieces = board.getProPieces();
        int boardLength = board.getLength();
        Point newCoordinates = new Point((int)(e.getX()/(boardLength/8)),(int)(e.getY()/(boardLength/8)));

        if (moves(squares).contains(newCoordinates)){
            squares.get(coordinates).setPiece(null);

            if (squares.get(newCoordinates).getPiece()!=null){
                pieces.remove(squares.get(newCoordinates).getPiece());
            }

            squares.get(newCoordinates).setPiece(this);

            coordinates = newCoordinates;

            imageCorner.x = newCoordinates.x*(boardLength/8);
            imageCorner.y = newCoordinates.y*(boardLength/8);

            bounds.setLocation(imageCorner.x, imageCorner.y);
            isMoved = true;

            if (this instanceof Pawn){
                int countY;
                if (isWhite){
                    if (coordinates.y==0){
                        isPromoting = true;
                        countY = 0;
                        for (Piece p : proPieces){
                            p.setX(this.coordinates.x);
                            p.setY(countY);
                            p.setImageCornerX(imageCorner.x);
                            p.setImageCornerY(countY*image.getIconHeight());
                            p.setBoundsX(imageCorner.x);
                            p.setBoundsY(countY*image.getIconHeight());
                            p.setWhite(true);
                            p.updateImage();

                            countY++;
                        }
                    }
                }
                else{
                    if (coordinates.y==7){
                        isPromoting = true;
                        countY = 7;
                        for (Piece p : proPieces){
                            p.setX(this.coordinates.x);
                            p.setY(countY);
                            p.setImageCornerX(imageCorner.x);
                            p.setImageCornerY(countY*image.getIconHeight());
                            p.setBoundsX(imageCorner.x);
                            p.setBoundsY(countY*image.getIconHeight());
                            p.setWhite(false);
                            p.updateImage();

                            countY--;

                        }
                    }
                }
            }
        }
        else{
            imageCorner.x = coordinates.x*(boardLength/8);
            imageCorner.y = coordinates.y*(boardLength/8);
        }
    }

    public ArrayList<Point> moves(Map<Point, Square> squares){
        ArrayList<Point> points = getPoints();

        ArrayList<Point> coordinates = new ArrayList<>();
        for (Point p : points){

            if (squares.get(p).getPiece()==null){
                coordinates.add(p);
            }
            else if(squares.get(p).getPiece()!=null && squares.get(p).getPiece().isWhite!=isWhite){
                coordinates.add(p);
            }
        }

        return coordinates;
    }

    public ArrayList<Point> attackedSquares(Map<Point, Square> squares){
        return getPoints();
    }

    public ArrayList<Point> getPoints(){
        return null;
    }

    public String getDirectoryW(){
        return null;
    }

    public String getDirectoryB(){
        return null;
    }

    public boolean areCoordinatesIn(Point coordinates){
        return !(coordinates.getX() < 0) && !(coordinates.getX() > 7) && !(coordinates.getY() < 0) && !(coordinates.getY() > 7);
    }

    public boolean isTherePiece(Map<Point, Square> squares, Point p) {
        return squares.get(p).getPiece() != null;
    }
}
