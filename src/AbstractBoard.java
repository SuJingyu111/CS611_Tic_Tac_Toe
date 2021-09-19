public abstract class AbstractBoard {

    protected AbstractPiece[][] view;

    protected int r, c;

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

    public String toDisplayString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < r; i++) {
            res.append("+--".repeat(Math.max(0, c)));
            res.append("+\n");
            for (int j = 0; j < c; j++) {
                res.append('|');
                res.append(view[i][j] == null ? "  " : view[i][j].getName() + " ");
            }
            res.append("|\n");
        }
        res.append("+--".repeat(Math.max(0, c)));
        res.append("+\n");
        return res.toString();
    }

    public boolean isValidPos(int row, int col) {
        return view[row][col] == null;
    }

    public boolean inBound(int row, int col) {
        return row < r || row >= 0 || col < c || col >= 0;
    }

    public String getPieceName(int row, int col) {
        return view[row][col] == null ? " " : view[row][col].getName();
    }

    public abstract void put(int row, int col, AbstractPiece piece);

    public abstract void clear();

    public abstract void display();

    public abstract boolean isDraw();
}
