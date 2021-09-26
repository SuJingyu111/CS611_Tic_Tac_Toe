class Board extends AbstractBoard {

    protected int winningCriterion;

    protected int remainingPos;

    public Board() {
        super();
        winningCriterion = 3;
        remainingPos = 9;
        pieceNameMaxLen = 1;
    }

    public Board(int r, int c, int winningCriterion) {
        super(r, c);
        this.winningCriterion = winningCriterion;
        remainingPos = r * c;
        pieceNameMaxLen = 1;
    }

    public boolean isFull() {
        return remainingPos == 0;
    }

    @Override
    public void put(int row, int col, AbstractPiece piece) {
        remainingPos--;
        view[row][col] = piece;
    }

    @Override
    public void clear() {
        view = new AbstractPiece[r][c];
        remainingPos = r * c;
    }

    @Override
    public void display() {
        System.out.println(toDisplayString());
    }

    @Override
    public int[] getParameters() {
        return new int[]{r, c};
    }

    @Override
    public int getWinningCriterion() {
        return winningCriterion;
    }
}
