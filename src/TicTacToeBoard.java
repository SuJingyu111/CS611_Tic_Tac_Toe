class TicTacToeBoard extends AbstractBoard {

    private int winningCriterion;

    private int remainingPos;

    public TicTacToeBoard() {
        super();
        winningCriterion = 3;
        remainingPos = 9;
    }

    public TicTacToeBoard(int r, int c, int winningCriterion) {
        super(r, c);
        this.winningCriterion = winningCriterion;
    }

    /*
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < r; i++) {
            res.append("+--".repeat(Math.max(0, c)));
            res.append("+\n");
            for (int j = 0; j < c; j++) {
                res.append('|');
                res.append(view[i][j] == null ? "  " : view[i][j] + " ");
            }
            res.append("|\n");
        }
        res.append("+--".repeat(Math.max(0, c)));
        res.append("+\n");
        return res.toString();
    }
    */

    public void put(int row, int col, AbstractPiece piece) {
        remainingPos--;
        view[row][col] = piece;
    }

    public void clear() {
        view = new AbstractPiece[r][c];
        remainingPos = r * c;
    }

    public void display() {
        System.out.println(this);
    }

    public int[] getParameters() {
        return new int[]{r, c};
    }

    public String getPieceName(int row, int col) {
        return view[row][col] == null ? " " : view[row][col].getName();
    }

    public int getWinningCriterion() {
        return winningCriterion;
    }

    public boolean isDraw() {
        return remainingPos == 0;
    }
}
