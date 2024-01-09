package Board.Pieces;

import Board.Square;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class Bishop extends Piece{
    public Bishop(boolean isWhite, int boardLength, int x, int y){
        super(isWhite, boardLength, x, y);
    }

    @Override
    public String getDirectoryW() {
        return "./img/WhiteBishop.png";
    }

    @Override
    public String getDirectoryB() {
        return "./img/BlackBishop.png";
    }

    @Override
    public ArrayList<Point> moves(Map<Point, Square> squares) {
        ArrayList<Point> coordinates = new ArrayList<>();

        Point p = new Point(this.coordinates.x+1,this.coordinates.y+1);
        while(areCoordinatesIn(p)){
            if (isTherePiece(squares, coordinates, p)) break;
            p.setLocation(p.x+1,p.y+1);
        }

        p.x=this.coordinates.x+1;
        p.y=this.coordinates.y-1;
        while(areCoordinatesIn(p)){
            if (isTherePiece(squares, coordinates, p)) break;
            p.setLocation(p.x+1,p.y-1);
        }

        p.x=this.coordinates.x-1;
        p.y=this.coordinates.y+1;
        while(areCoordinatesIn(p)){
            if (isTherePiece(squares, coordinates, p)) break;
            p.setLocation(p.x-1,p.y+1);
        }

        p.x=this.coordinates.x-1;
        p.y=this.coordinates.y-1;
        while(areCoordinatesIn(p)){
            if (isTherePiece(squares, coordinates, p)) break;
            p.setLocation(p.x-1,p.y-1);
        }

        return coordinates;
    }


}
