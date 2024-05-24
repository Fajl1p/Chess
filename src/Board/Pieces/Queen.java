package Board.Pieces;

import Board.Square;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class Queen extends Piece{
    public Queen(boolean isWhite, int boardLength, int x, int y) {
        super(isWhite, boardLength, x, y);
    }

    @Override
    public String getDirectoryW() {
        return "./img/WhiteQueen.png";
    }

    @Override
    public String getDirectoryB() {
        return "./img/BlackQueen.png";
    }

    public ArrayList<Point> moves(Map<Point, Square> squares) {
        ArrayList<Point> coordinates = new ArrayList<>();

        Point p = new Point(this.coordinates.x+1,this.coordinates.y+1);
        while(areCoordinatesIn(p)){
            if (isTherePiece(squares, p)){
                if (squares.get(p).getPiece().isWhite != this.isWhite) {
                    coordinates.add(new Point(p.x, p.y));
                }
                break;
            }
            else {
                coordinates.add(new Point(p.x,p.y));
            }
            p.setLocation(p.x+1,p.y+1);
        }

        p.x=this.coordinates.x+1;
        p.y=this.coordinates.y-1;
        while(areCoordinatesIn(p)){
            if (isTherePiece(squares, p)){
                if (squares.get(p).getPiece().isWhite != this.isWhite) {
                    coordinates.add(new Point(p.x, p.y));
                }
                break;
            }
            else {
                coordinates.add(new Point(p.x,p.y));
            }
            p.setLocation(p.x+1,p.y-1);
        }

        p.x=this.coordinates.x-1;
        p.y=this.coordinates.y+1;
        while(areCoordinatesIn(p)){
            if (isTherePiece(squares, p)){
                if (squares.get(p).getPiece().isWhite != this.isWhite) {
                    coordinates.add(new Point(p.x, p.y));
                }
                break;
            }
            else {
                coordinates.add(new Point(p.x,p.y));
            }
            p.setLocation(p.x-1,p.y+1);
        }

        p.x=this.coordinates.x-1;
        p.y=this.coordinates.y-1;
        while(areCoordinatesIn(p)){
            if (isTherePiece(squares, p)){
                if (squares.get(p).getPiece().isWhite != this.isWhite) {
                    coordinates.add(new Point(p.x, p.y));
                }
                break;
            }
            else {
                coordinates.add(new Point(p.x,p.y));
            }
            p.setLocation(p.x-1,p.y-1);
        }


        p.x = this.coordinates.x+1;
        p.y = this.coordinates.y;
        while(areCoordinatesIn(p)){
            if (isTherePiece(squares, p)){
                if (squares.get(p).getPiece().isWhite != this.isWhite) {
                    coordinates.add(new Point(p.x, p.y));
                }
                break;
            }
            else {
                coordinates.add(new Point(p.x,p.y));
            }
            p.setLocation(p.x+1,p.y);
        }

        p.x=this.coordinates.x;
        p.y=this.coordinates.y+1;
        while(areCoordinatesIn(p)){
            if (isTherePiece(squares, p)){
                if (squares.get(p).getPiece().isWhite != this.isWhite) {
                    coordinates.add(new Point(p.x, p.y));
                }
                break;
            }
            else {
                coordinates.add(new Point(p.x,p.y));
            }
            p.setLocation(p.x,p.y+1);
        }

        p.x=this.coordinates.x-1;
        p.y=this.coordinates.y;
        while(areCoordinatesIn(p)){
            if (isTherePiece(squares, p)){
                if (squares.get(p).getPiece().isWhite != this.isWhite) {
                    coordinates.add(new Point(p.x, p.y));
                }
                break;
            }
            else {
                coordinates.add(new Point(p.x,p.y));
            }
            p.setLocation(p.x-1,p.y);
        }

        p.x=this.coordinates.x;
        p.y=this.coordinates.y-1;
        while(areCoordinatesIn(p)){
            if (isTherePiece(squares, p)){
                if (squares.get(p).getPiece().isWhite != this.isWhite) {
                    coordinates.add(new Point(p.x, p.y));
                }
                break;
            }
            else {
                coordinates.add(new Point(p.x,p.y));
            }
            p.setLocation(p.x,p.y-1);
        }

        return coordinates;
    }

    @Override
    public ArrayList<Point> attackedSquares(Map<Point, Square> squares){
        ArrayList<Point> coordinates = new ArrayList<>();

        Point p = new Point(this.coordinates.x+1,this.coordinates.y+1);
        while(areCoordinatesIn(p)){
            coordinates.add(new Point(p.x, p.y));
            if (isTherePiece(squares, p)){
                break;
            }
            p.setLocation(p.x+1,p.y+1);
        }

        p.x=this.coordinates.x+1;
        p.y=this.coordinates.y-1;
        while(areCoordinatesIn(p)){
            coordinates.add(new Point(p.x, p.y));
            if (isTherePiece(squares, p)){
                break;
            }
            p.setLocation(p.x+1,p.y-1);
        }

        p.x=this.coordinates.x-1;
        p.y=this.coordinates.y+1;
        while(areCoordinatesIn(p)){
            coordinates.add(new Point(p.x, p.y));
            if (isTherePiece(squares, p)){
                break;
            }
            p.setLocation(p.x-1,p.y+1);
        }

        p.x=this.coordinates.x-1;
        p.y=this.coordinates.y-1;
        while(areCoordinatesIn(p)){
            coordinates.add(new Point(p.x, p.y));
            if (isTherePiece(squares, p)){
                break;
            }
            p.setLocation(p.x-1,p.y-1);
        }


        p.x = this.coordinates.x+1;
        p.y = this.coordinates.y;
        while(areCoordinatesIn(p)){
            coordinates.add(new Point(p.x, p.y));
            if (isTherePiece(squares, p)){
                break;
            }
            p.setLocation(p.x+1,p.y);
        }

        p.x=this.coordinates.x;
        p.y=this.coordinates.y+1;
        while(areCoordinatesIn(p)){
            coordinates.add(new Point(p.x, p.y));
            if (isTherePiece(squares, p)){
                break;
            }
            p.setLocation(p.x,p.y+1);
        }

        p.x=this.coordinates.x-1;
        p.y=this.coordinates.y;
        while(areCoordinatesIn(p)){
            coordinates.add(new Point(p.x, p.y));
            if (isTherePiece(squares, p)){
                break;
            }
            p.setLocation(p.x-1,p.y);
        }

        p.x=this.coordinates.x;
        p.y=this.coordinates.y-1;
        while(areCoordinatesIn(p)){
            coordinates.add(new Point(p.x, p.y));
            if (isTherePiece(squares, p)){
                break;
            }
            p.setLocation(p.x,p.y-1);
        }

        return coordinates;
    }
}
