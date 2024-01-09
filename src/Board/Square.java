package Board;

import Board.Pieces.Piece;

public class Square {
    boolean isAttackedByWhite = false;
    boolean isAttackedByBlack = false;
    Piece piece = null;

    public boolean isAttackedByWhite() {
        return isAttackedByWhite;
    }

    public void setAttackedByWhite(boolean attackedByWhite) {
        isAttackedByWhite = attackedByWhite;
    }

    public boolean isAttackedByBlack() {
        return isAttackedByBlack;
    }

    public void setAttackedByBlack(boolean attackedByBlack) {
        isAttackedByBlack = attackedByBlack;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
