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

    public void put(int row, int col, AbstractPiece piece) {
        remainingPos--;
        view[row][col] = piece;
    }

    public void clear() {
        view = new AbstractPiece[r][c];
        remainingPos = r * c;
    }

    public void display() {
        System.out.println(toDisplayString());
    }

    public int[] getParameters() {
        return new int[]{r, c};
    }

    public int getWinningCriterion() {
        return winningCriterion;
    }

    public boolean isDraw() {
        return remainingPos == 0;
    }
}
