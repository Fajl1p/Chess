package Board;

import Board.Pieces.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {
    ArrayList<Piece> pieces = new ArrayList<>();
    Map<Point, Square> squares = new HashMap<>();
    ArrayList<Piece> proPieces = new ArrayList<>();
    int length;

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public Map<Point, Square> getSquares() {
        return squares;
    }

    public ArrayList<Piece> getProPieces() {
        return proPieces;
    }

    public int getLength() {
        return length;
    }

    public Board(int length){
        this.length=length;
        Knight knight = new Knight(true, length, 5,5);
        King king = new King(false, length, 2,2);
        King king2 = new King(true, length, 5,4);

        Bishop bishop = new Bishop(false, length, 4,4);
        Rook rook = new Rook(false, length, 0,0);
        Pawn pawn = new Pawn(true, length, 1,1);
        Pawn pawn2 = new Pawn(false, length, 2,6);
        Pawn pawn3 = new Pawn(true, length, 6,6);
        Queen queen = new Queen(true, length, 7,7);

        Queen proQueen = new Queen(true, length, 1,0);
        Rook proRook = new Rook(true, length, 1,1);
        Knight proknight = new Knight(true, length, 1,2);
        Bishop proBishop = new Bishop(true, length, 1,3);

        pieces.add(knight);
        pieces.add(king);
        pieces.add(king2);
        pieces.add(bishop);
        pieces.add(rook);
        pieces.add(pawn);
        pieces.add(pawn2);
        pieces.add(pawn3);
        pieces.add(queen);

        proPieces.add(proQueen);
        proPieces.add(proRook);
        proPieces.add(proknight);
        proPieces.add(proBishop);


        for (int x = 0; x<8; x++){
            for (int y = 0; y<8; y++){
                squares.put(new Point(x,y), new Square());
            }
        }

        for (Piece p : pieces){
            squares.get(p.getCoordinates()).setPiece(p);
        }

    }

    public void paintBoard(Graphics g, Color white, Color black){
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
    }

    public void paintPieces(Component c,Graphics g ,boolean isPromoting){
        for (Piece p : pieces){
            p.paint(c, g);
        }

        if (isPromoting){
            for (Piece p : proPieces){
                g.setColor(Color.WHITE);
                g.fillRect((int)p.getCoordinates().getX()*(length/8),(int)p.getCoordinates().getY()*(length/8),length/8,length/8);

                p.paint(c, g);
            }
        }
    }

    public void promote(Piece piece, Piece piece2){
        Piece newPiece = piece.promote(piece2);
        pieces.add(newPiece);
        squares.get(piece.getCoordinates()).setPiece(newPiece);

        pieces.remove(piece);
    }

}
