package Board.Pieces;

import Board.Square;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class Pawn extends Piece{
    public Pawn(boolean isWhite, int boardLength, int x, int y) {
        super(isWhite, boardLength, x, y);
    }

    @Override
    public String getDirectoryW() {
        return "./img/WhitePawn.png";
    }

    @Override
    public String getDirectoryB() {
        return "./img/BlackPawn.png";
    }

    @Override
    public ArrayList<Point> moves(Map<Point, Square> squares) {
        ArrayList<Point> coordinates = new ArrayList<>();

        Point p;
        Point p2;
        Point p3;
        Point p4;

        if (this.isWhite){
            p = new Point(this.coordinates.x, this.coordinates.y-1);
            p2 = new Point(this.coordinates.x, this.coordinates.y-2);
            p3 = new Point(this.coordinates.x-1, this.coordinates.y-1);
            p4 = new Point(this.coordinates.x+1, this.coordinates.y-1);
        }
        else{
            p = new Point(this.coordinates.x, this.coordinates.y+1);
            p2 = new Point(this.coordinates.x, this.coordinates.y+2);
            p3 = new Point(this.coordinates.x-1, this.coordinates.y+1);
            p4 = new Point(this.coordinates.x+1, this.coordinates.y+1);
        }

        if (areCoordinatesIn(p) && squares.get(p).getPiece()==null){
            coordinates.add(p);
        }

        if (areCoordinatesIn(p) && areCoordinatesIn(p2) && squares.get(p).getPiece()==null && squares.get(p2).getPiece()==null && !isMoved){
            coordinates.add(p2);
        }

        if (areCoordinatesIn(p3) && squares.get(p3).getPiece()!=null && squares.get(p3).getPiece().isWhite!=isWhite){
            coordinates.add(p3);
        }

        if (areCoordinatesIn(p4) && squares.get(p4).getPiece()!=null && squares.get(p4).getPiece().isWhite!=isWhite){
            coordinates.add(p4);
        }
        return coordinates;
    }

    @Override
    public ArrayList<Point> attackedSquares(Map<Point, Square> squares){
        ArrayList<Point> coordinates = new ArrayList<>();

        if (this.isWhite){
            coordinates.add(new Point(this.coordinates.x-1, this.coordinates.y-1));
            coordinates.add(new Point(this.coordinates.x+1, this.coordinates.y-1));
        }
        else{
            coordinates.add(new Point(this.coordinates.x-1, this.coordinates.y+1));
            coordinates.add(new Point(this.coordinates.x+1, this.coordinates.y+1));
        }

        coordinates.removeIf(p -> !areCoordinatesIn(p));
        return coordinates;
    }
}
