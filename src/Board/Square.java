package Board;

import Board.Pieces.Piece;

public class Square {
    boolean isAttackedByWhite = false;
    boolean isAttackedByBlack = false;

    boolean isAttacked = false;
    Piece piece = null;


    public boolean isAttacked() {
        return isAttacked;
    }

    public void setAttacked() {
        isAttacked = true;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void removeAttacks() {
        isAttackedByWhite = false;
        isAttackedByBlack = false;

        isAttacked = false;
    }
}
