package Board.Pieces;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Knight extends Piece{
    public Knight(boolean isWhite, int boardLength, int x, int y){
        super(isWhite, boardLength, x, y);
    }

    @Override
    public String getDirectoryW() {
        return "./img/WhiteKnight.png";
    }

    @Override
    public String getDirectoryB() {
        return "./img/BlackKnight.png";
    }

    @Override
    public ArrayList<Point> getPoints() {
        ArrayList<Point> points = new ArrayList<>();

        points.add(new Point(coordinates.x+2, coordinates.y+1));
        points.add(new Point(coordinates.x+2, coordinates.y-1));
        points.add(new Point(coordinates.x-2, coordinates.y+1));
        points.add(new Point(coordinates.x-2, coordinates.y-1));

        points.add(new Point(coordinates.x+1, coordinates.y+2));
        points.add(new Point(coordinates.x-1, coordinates.y+2));
        points.add(new Point(coordinates.x+1, coordinates.y-2));
        points.add(new Point(coordinates.x-1, coordinates.y-2));

        points.removeIf(p -> !areCoordinatesIn(p));
        return points;
    }
}
