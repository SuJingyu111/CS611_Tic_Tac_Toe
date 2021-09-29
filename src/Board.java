/** Board on which TicTacToe-like games are played */

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

    /** Check if there are no empty places for a new piece */
    public boolean isFull() {
        return remainingPos == 0;
    }

    /** Puts a piece on a position */
    @Override
    public void put(int row, int col, AbstractPiece piece) {
        remainingPos--;
        view[row][col] = piece;
    }

    /** Clears the board */
    @Override
    public void clear() {
        view = new AbstractPiece[r][c];
        remainingPos = r * c;
    }

    /** Displays the board */
    @Override
    public void display() {
        System.out.println(toDisplayString());
    }

    /** Return the length and width */
    @Override
    public int[] getParameters() {
        return new int[]{r, c};
    }

    /** Returns the wining criterion */
    @Override
    public int getWinningCriterion() {
        return winningCriterion;
    }
}
