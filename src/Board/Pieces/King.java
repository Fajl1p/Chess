package Board.Pieces;

import java.awt.*;
import java.util.ArrayList;

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

        return points;
    }
}
