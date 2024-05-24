package Board;

import Board.Pieces.Piece;

public class Square {
    boolean isAttackedByWhite = false;
    boolean isAttackedByBlack = false;
    Piece piece = null;

    public boolean isAttackedByWhite() {
        return isAttackedByWhite;
    }

    public void setAttackedByWhite() {
        isAttackedByWhite = true;
    }

    public boolean isAttackedByBlack() {
        return isAttackedByBlack;
    }

    public void setAttackedByBlack() {
        isAttackedByBlack = true;
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
    }
}
