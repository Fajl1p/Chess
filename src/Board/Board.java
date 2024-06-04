package Board;

import Board.Pieces.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {
    //ArrayList<Piece> pieces = new ArrayList<>();
    Map<Point, Square> squares = new HashMap<>();
    ArrayList<Piece> proPieces = new ArrayList<>();
    int length;

    ArrayList<Piece> piecesWhite = new ArrayList<>();
    ArrayList<Piece> piecesBlack = new ArrayList<>();

    boolean isWhiteTurn = true;

    /*public ArrayList<Piece> getPieces() {
        return pieces;
    }*/

    public ArrayList<Piece> getPieces() {
        if (isWhiteTurn){
            return piecesWhite;
        }
        else{
            return piecesBlack;
        }
    }

    public ArrayList<Piece> getOpponentPieces() {
        if (isWhiteTurn){
            return piecesBlack;
        }
        else{
            return piecesWhite;
        }
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

    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }

    public void setWhiteTurn(boolean whiteTurn) {
        isWhiteTurn = whiteTurn;
    }

    public Board(int length){
        this.length=length;
        Knight knight = new Knight(true, length, 5,3);
        King king = new King(false, length, 2,2);
        King king2 = new King(true, length, 6,2);

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

        /*pieces.add(knight);
        pieces.add(king);
        pieces.add(king2);
        pieces.add(bishop);
        pieces.add(rook);
        pieces.add(pawn);
        pieces.add(pawn2);
        pieces.add(pawn3);
        pieces.add(queen);*/

        piecesWhite.add(knight);
        piecesWhite.add(king2);
        piecesWhite.add(pawn);
        piecesWhite.add(pawn3);
        piecesWhite.add(queen);

        piecesBlack.add(king);
        piecesBlack.add(bishop);
        piecesBlack.add(rook);
        piecesBlack.add(pawn2);



        proPieces.add(proQueen);
        proPieces.add(proRook);
        proPieces.add(proknight);
        proPieces.add(proBishop);


        for (int x = 0; x<8; x++){
            for (int y = 0; y<8; y++){
                squares.put(new Point(x,y), new Square());
            }
        }

        /*for (Piece p : pieces){
            squares.get(p.getCoordinates()).setPiece(p);
        }*/

        for (Piece p : piecesWhite){
            squares.get(p.getCoordinates()).setPiece(p);
        }

        for (Piece p : piecesBlack){
            squares.get(p.getCoordinates()).setPiece(p);
        }

        setAttackedSquares();
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
        /*for (Piece p : pieces){
            p.paint(c, g);
        }*/

        for (Piece p : piecesWhite){
            p.paint(c, g);
        }

        for (Piece p : piecesBlack){
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
        //pieces.add(newPiece);
        getPieces().add(newPiece);
        squares.get(piece.getCoordinates()).setPiece(newPiece);

        //pieces.remove(piece);
        getPieces().remove(piece);

        isWhiteTurn = !isWhiteTurn;
    }

    public void updateSquares(){
        //remove attacked squares
        for (Square s : squares.values()) {
            s.removeAttacks();
        }

        //set attacked squares
        /*for (Piece p : pieces){
            for (Point attackedPoint : p.attackedSquares(squares)){
                if (p.isWhite()){
                    squares.get(attackedPoint).setAttackedByWhite();
                }
                else{
                    squares.get(attackedPoint).setAttackedByBlack();
                }
            }
        }*/

        setAttackedSquares();
    }


    public void setAttackedSquares(){
        /*for (Piece p : pieces){
            for (Point attackedPoint : p.attackedSquares(squares)){
                if (p.isWhite()){
                    squares.get(attackedPoint).setAttackedByWhite();
                }
                else{
                    squares.get(attackedPoint).setAttackedByBlack();
                }
            }
        }*/

        for (Piece p : getOpponentPieces()){
            for (Point attackedPoint : p.attackedSquares(squares)){
                if (p.isWhite()){
                    squares.get(attackedPoint).setAttackedByWhite();
                }
                else{
                    squares.get(attackedPoint).setAttackedByBlack();
                }
            }
        }
    }

    public boolean isKingNotAttacked(){
        //find king in pieces
        //get coordinates from piece and get square by it
        //if king is on attacked square = true, else false

        Piece king = null;

        for (Piece p : getPieces()){
            if (p instanceof King){
                king = p;
                System.out.println("King is found");
                break;
            }
        }



        if (isWhiteTurn){
            if(squares.get(king.getCoordinates()).isAttackedByBlack()){
                //System.out.println("White king is attacked");
                return false;
            }
            //System.out.println("White king is not attacked");
            return true;
        }
        else{
            if(squares.get(king.getCoordinates()).isAttackedByWhite()){
                //System.out.println("Black king is attacked");
                return false;
            }
            //System.out.println("Black king is not attacked");
            return true;
        }
    }
}
