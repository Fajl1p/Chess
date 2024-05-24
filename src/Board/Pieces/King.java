package Board.Pieces;

import Board.Square;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class King extends Piece{
    public King(boolean isWhite, int boardLength, int x, int y){
        super(isWhite, boardLength, x, y);
    }

    @Override
    public String getDirectoryW() {
        return "./img/WhiteKing.png";
    }

    @Override
    public String getDirectoryB() {
        return "./img/BlackKing.png";
    }

    @Override
    public ArrayList<Point> getPoints() {
        ArrayList<Point> points = new ArrayList<>();

        points.add(new Point(coordinates.x-1, coordinates.y-1));
        points.add(new Point(coordinates.x, coordinates.y-1));
        points.add(new Point(coordinates.x+1, coordinates.y-1));

        points.add(new Point(coordinates.x-1, coordinates.y));
        points.add(new Point(coordinates.x+1, coordinates.y));

        points.add(new Point(coordinates.x-1, coordinates.y+1));
        points.add(new Point(coordinates.x, coordinates.y+1));
        points.add(new Point(coordinates.x+1, coordinates.y+1));

        points.removeIf(p -> !areCoordinatesIn(p));

        return points;
    }

    @Override
    public ArrayList<Point> moves(Map<Point, Square> squares){
        ArrayList<Point> points = getPoints();

        ArrayList<Point> coordinates = new ArrayList<>();
        for (Point p : points){

            if (areCoordinatesIn(p) && squares.get(p).getPiece()==null && (!squares.get(p).isAttackedByBlack() && isWhite) || (!squares.get(p).isAttackedByWhite() && !isWhite)){
                coordinates.add(p);
            }
            else if(areCoordinatesIn(p) && squares.get(p).getPiece()!=null && squares.get(p).getPiece().isWhite!=isWhite && (!squares.get(p).isAttackedByBlack() && isWhite) || (!squares.get(p).isAttackedByWhite() && !isWhite)){
                coordinates.add(p);
            }
        }

        return coordinates;
    }
}
