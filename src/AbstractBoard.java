/** Abstract class of board for board games */

public abstract class AbstractBoard {

    protected AbstractPiece[][] view;

    protected int r, c;

    protected int pieceNameMaxLen;

    public AbstractBoard() {
        view = new AbstractPiece[3][3];
        r = 3;
        c = 3;
    }

    public AbstractBoard(int r, int c) {
        view = new AbstractPiece[r][c];
        this.r = r;
        this.c = c;
    }

    /** Gives the visual display string of the board */
    public String toDisplayString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < r; i++) {
            res.append(("+" + "-".repeat(pieceNameMaxLen + 1)).repeat(Math.max(0, c)));
            res.append("+\n");
            for (int j = 0; j < c; j++) {
                res.append('|');
                res.append(view[i][j] == null ? " ".repeat(pieceNameMaxLen + 1) : view[i][j].getName() + " ");
            }
            res.append("|\n");
        }
        res.append(("+" + "-".repeat(pieceNameMaxLen + 1)).repeat(Math.max(0, c)));
        res.append("+\n");
        return res.toString();
    }

    /** Checks if a position is taken or not */
    public boolean isValidPos(int row, int col) {
        return view[row][col] == null;
    }

    /** Checks if a position is within the boundary of the board */
    public boolean inBound(int row, int col) {
        return row < r && row >= 0 && col < c && col >= 0;
    }

    /** Get the name of a piece at a position */
    public String getPieceName(int row, int col) {
        return view[row][col] == null ? " " : view[row][col].getName();
    }

    /** Get the owner of a piece at a position */
    public AbstractPlayer getPieceOwner(int row, int col) {
        return view[row][col] == null ? null : view[row][col].getOwner();
    }

    /** Put a piece at a specified position */
    public abstract void put(int row, int col, AbstractPiece piece);

    /** Clears the board for a new round */
    public abstract void clear();

    /** Displays the board */
    public abstract void display();

    /** Gets parameters related to the board */
    public abstract int[] getParameters();

    /** Gets winning criterion */
    public abstract int getWinningCriterion();
}
